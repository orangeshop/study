"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Hex = void 0;
const HexErrorResponse_1 = require("../../../common/HexErrorResponse");
class Hex {
    hex;
    constructor(hex) {
        this.hex = hex;
    }
    hexToRgb() {
        let removeFirst;
        if (this.hex.startsWith('#')) {
            removeFirst = this.hex.slice(1);
        }
        else {
            throw new HexErrorResponse_1.HexErrorResponse('# not found');
        }
        if (removeFirst.length !== 6) {
            throw new HexErrorResponse_1.HexErrorResponse('hex length is too long.');
        }
        return {
            r: this.toRgb(removeFirst, 0, 2),
            g: this.toRgb(removeFirst, 2, 4),
            b: this.toRgb(removeFirst, 4, 6),
        };
    }
    toRgb = (v, start, end) => {
        return parseInt(v.substr(start, end), 16);
    };
}
exports.Hex = Hex;
//# sourceMappingURL=Hex.js.map