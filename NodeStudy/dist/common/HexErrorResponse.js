"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.HexErrorResponse = void 0;
class HexErrorResponse extends Error {
    constructor(message) {
        super(message);
        this.name = 'HexErrorResponse';
        Object.setPrototypeOf(this, HexErrorResponse.prototype);
    }
}
exports.HexErrorResponse = HexErrorResponse;
//# sourceMappingURL=HexErrorResponse.js.map