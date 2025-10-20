"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Rgb = void 0;
class Rgb {
    r;
    g;
    b;
    constructor(r, g, b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
    RgbToHex() {
        return `#${this.toHex(this.r)}${this.toHex(this.g)}${this.toHex(this.b)}`;
    }
    toHex = (v) => {
        return v.toString(16).padStart(2, '0');
    };
}
exports.Rgb = Rgb;
//# sourceMappingURL=Rgb.js.map