import { Module } from '@nestjs/common';
import { RgbToHexService } from './service/RgbToHex.service';
import { RgbToHexController } from './controller/RgbToHex.controller';

@Module({
  controllers: [RgbToHexController],
  providers: [RgbToHexService],
})
export class RgbToHexModule {}
