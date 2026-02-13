package study.sqlstudy.inventory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import study.sqlstudy.inventory.entity.Inventory;
import study.sqlstudy.inventory.repository.InventoryRepository;
import study.sqlstudy.inventory.service.InventoryService;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql(scripts = "/sql/init-inventory.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class InventoryServiceTest {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryService inventoryService;

    @Test
    @DisplayName("init sql로 재고 데이터가 정상 적재된다")
    void initDataLoaded() {
        List<Inventory> inventories = inventoryRepository.findAll();

        assertThat(inventories).hasSize(3);
        assertThat(inventories).allSatisfy(inventory -> {
            assertThat(inventory.getQuantity()).isGreaterThan(0).isLessThan(10);
            assertThat(inventory.getProduct()).isNotNull();
        });
    }

    @Test
    @DisplayName("상품 ID로 재고를 조회한다")
    void findByProductId() {
        Inventory inventory = inventoryRepository.findByProductId(1).orElseThrow();

        assertThat(inventory.getProduct().getName()).isEqualTo("무선 키보드");
        assertThat(inventory.getQuantity()).isEqualTo(7);
    }

    @Test
    void 재고_동시성_테스트() throws InterruptedException {

        int taskCount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(taskCount);
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch latch = new CountDownLatch(taskCount);

        for (int i = 1; i <= taskCount; i++) {
            int taskId = i;
            executorService.submit(() -> {
                try {
                    startLatch.await();
                    System.out.println("task " + taskId + " 시작");
                    inventoryService.decreaseStock(1, 1);
                    System.out.println("task " + taskId + " 완료 (차감 성공)");
                } catch (Exception e) {
                    System.out.println("task " + taskId + " 예외: " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }

        startLatch.countDown();
        latch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();

        Inventory finalInventory = inventoryService.findByProductId(1);
        System.out.println("최종 재고: " + finalInventory.getQuantity());
        assertThat(finalInventory.getQuantity()).isEqualTo(0);
    }
}
