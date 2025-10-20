import { Body, Controller, Get, Post } from '@nestjs/common';
import { RgbToHexService } from '../service/RgbToHex.service';
import { RGBToHexRequest } from '../dto/request/RgbToHexRequest';

@Controller()
export class RgbToHexController {
  constructor(private readonly rgbToHexService: RgbToHexService) {}

  @Post('/rgb')
  transfer(@Body() rgbToHexRequest: RGBToHexRequest) {
    return this.rgbToHexService.transfer(
      rgbToHexRequest.r,
      rgbToHexRequest.g,
      rgbToHexRequest.b,
    );
  }
}
