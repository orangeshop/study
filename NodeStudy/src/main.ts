import { NestFactory } from '@nestjs/core';
import { HexErrorFilter } from './common/HexError.filter';
import { AppModule } from './common/AppModule';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  app.useGlobalFilters(new HexErrorFilter());
  await app.listen(process.env.PORT ?? 3000);
}

bootstrap();
