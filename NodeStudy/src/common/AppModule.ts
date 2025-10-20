import { Module } from '@nestjs/common';
import { HexToRgbModule } from '../hex-to-rgb/hex/HexToRgb.module';
import { RgbToHexModule } from '../hex-to-rgb/rgb/RgbToHex.module';

@Module({
  imports: [HexToRgbModule, RgbToHexModule],
  controllers: [],
  providers: [],
})
export class AppModule {}
