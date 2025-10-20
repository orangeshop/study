import { Injectable } from '@nestjs/common';
import { Hex } from '../domain/Hex';
import { HexToRgbRequest } from '../dto/request/HexToRgbRequest';

@Injectable()
export class HexToRgbService {
  transfer(v: HexToRgbRequest): { r: number; g: number; b: number } {
    const hex = new Hex(v.hex);
    return hex.hexToRgb();
  }
}
