import { Injectable } from '@nestjs/common';
import { Rgb } from '../domain/Rgb';

@Injectable()
export class RgbToHexService {
  transfer(r: number, g: number, b: number): string {
    const rgb = new Rgb(r, g, b);
    return rgb.RgbToHex();
  }
}
