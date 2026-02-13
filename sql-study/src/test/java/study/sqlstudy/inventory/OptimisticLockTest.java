package study.sqlstudy.inventory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import study.sqlstudy.inventory.entity.Inventory;
import study.sqlstudy.inventory.service.InventoryService;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql(scripts = "/sql/init-inventory.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class OptimisticLockTest {

    @Autowired
    private InventoryService inventoryService;

    @Test
    @DisplayName("낙관적 락 (고정 backoff): 동시 재고 차감 시 충돌로 일부 실패")
    void 고정_backoff_낙관적_락() throws InterruptedException {
        int taskCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(taskCount);
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch doneLatch = new CountDownLatch(taskCount);

        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger failCount = new AtomicInteger(0);

        for (int i = 1; i <= taskCount; i++) {
            int taskId = i;
            executor.submit(() -> {
                try {
                    startLatch.await();
                    inventoryService.decreaseStockOptimistic(1, 1);
                    successCount.incrementAndGet();
                    System.out.println("task " + taskId + " 성공");
                } catch (Exception e) {
                    failCount.incrementAndGet();
                    System.out.println("task " + taskId + " 실패: " + e.getClass().getSimpleName());
                } finally {
                    doneLatch.countDown();
                }
            });
        }

        startLatch.countDown();
        doneLatch.await(10, TimeUnit.SECONDS);
        executor.shutdown();

        Inventory finalInventory = inventoryService.findByProductId(1);
        System.out.println("=== 고정 backoff 결과 ===");
        System.out.println("성공: " + successCount.get() + ", 실패: " + failCount.get());
        System.out.println("최종 재고: " + finalInventory.getQuantity());

        assertThat(successCount.get() + failCount.get()).isEqualTo(taskCount);
        assertThat(finalInventory.getQuantity()).isEqualTo(7 - successCount.get());
        assertThat(failCount.get()).isGreaterThan(0);
    }

    @Test
    @DisplayName("낙관적 락 (랜덤 backoff): 충돌 분산으로 더 많이 성공")
    void 랜덤_backoff_낙관적_락() throws InterruptedException {
        int taskCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(taskCount);
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch doneLatch = new CountDownLatch(taskCount);

        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger failCount = new AtomicInteger(0);

        for (int i = 1; i <= taskCount; i++) {
            int taskId = i;
            executor.submit(() -> {
                try {
                    startLatch.await();
                    inventoryService.decreaseStockOptimisticRandom(1, 1);
                    successCount.incrementAndGet();
                    System.out.println("task " + taskId + " 성공");
                } catch (Exception e) {
                    failCount.incrementAndGet();
                    System.out.println("task " + taskId + " 실패: " + e.getClass().getSimpleName());
                } finally {
                    doneLatch.countDown();
                }
            });
        }

        startLatch.countDown();
        doneLatch.await(10, TimeUnit.SECONDS);
        executor.shutdown();

        Inventory finalInventory = inventoryService.findByProductId(1);
        System.out.println("=== 랜덤 backoff 결과 ===");
        System.out.println("성공: " + successCount.get() + ", 실패: " + failCount.get());
        System.out.println("최종 재고: " + finalInventory.getQuantity());

        assertThat(successCount.get() + failCount.get()).isEqualTo(taskCount);
        assertThat(finalInventory.getQuantity()).isEqualTo(7 - successCount.get());
    }
}
