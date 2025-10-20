import { Body, Controller, Post } from '@nestjs/common';
import { HexToRgbService } from '../service/HexToRgb.service';
import { HexToRgbRequest } from '../dto/request/HexToRgbRequest';

@Controller()
export class HexToRgbController {
  constructor(private readonly hexToRgbService: HexToRgbService) {}

  @Post('/hex')
  transfer(@Body() hexToRgbRequest: HexToRgbRequest): {
    r: number;
    g: number;
    b: number;
  } {
    return this.hexToRgbService.transfer(hexToRgbRequest);
  }
}
