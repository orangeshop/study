"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.HexToRgbService = void 0;
const common_1 = require("@nestjs/common");
const Hex_1 = require("../domain/Hex");
let HexToRgbService = class HexToRgbService {
    transfer(v) {
        const hex = new Hex_1.Hex(v.hex);
        return hex.hexToRgb();
    }
};
exports.HexToRgbService = HexToRgbService;
exports.HexToRgbService = HexToRgbService = __decorate([
    (0, common_1.Injectable)()
], HexToRgbService);
//# sourceMappingURL=HexToRgb.service.js.map