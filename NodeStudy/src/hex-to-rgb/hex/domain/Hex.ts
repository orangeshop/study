import { HexErrorResponse } from '../../../common/HexErrorResponse';

export class Hex {
  hex: string;

  constructor(hex: string) {
    this.hex = hex;
  }

  hexToRgb(): { r: number; g: number; b: number } {
    let removeFirst: string;

    if (this.hex.startsWith('#')) {
      removeFirst = this.hex.slice(1);
    } else {
      throw new HexErrorResponse('# not found');
    }

    if (removeFirst.length !== 6) {
      throw new HexErrorResponse('hex length is too long.');
    }

    return {
      r: this.toRgb(removeFirst, 0, 2),
      g: this.toRgb(removeFirst, 2, 4),
      b: this.toRgb(removeFirst, 4, 6),
    };
  }

  toRgb = (v: string, start: number, end: number): number => {
    return parseInt(v.substr(start, end), 16);
  };
}
