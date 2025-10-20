export class Rgb {
  r: number;
  g: number;
  b: number;

  constructor(r: number, g: number, b: number) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  RgbToHex(): string {
    return `#${this.toHex(this.r)}${this.toHex(this.g)}${this.toHex(this.b)}`;
  }

  toHex = (v: number): string => {
    return v.toString(16).padStart(2, '0');
  };
}
