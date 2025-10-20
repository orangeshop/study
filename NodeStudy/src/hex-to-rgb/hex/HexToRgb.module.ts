import { Module } from '@nestjs/common';
import { HexToRgbController } from './controller/HexToRgb.controller';
import { HexToRgbService } from './service/HexToRgb.service';

@Module({
  controllers: [HexToRgbController],
  providers: [HexToRgbService],
})
export class HexToRgbModule {}
