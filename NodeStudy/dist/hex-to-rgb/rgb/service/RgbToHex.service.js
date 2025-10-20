"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.RgbToHexService = void 0;
const common_1 = require("@nestjs/common");
const Rgb_1 = require("../domain/Rgb");
let RgbToHexService = class RgbToHexService {
    transfer(r, g, b) {
        const rgb = new Rgb_1.Rgb(r, g, b);
        return rgb.RgbToHex();
    }
};
exports.RgbToHexService = RgbToHexService;
exports.RgbToHexService = RgbToHexService = __decorate([
    (0, common_1.Injectable)()
], RgbToHexService);
//# sourceMappingURL=RgbToHex.service.js.map