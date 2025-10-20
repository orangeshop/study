"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var __param = (this && this.__param) || function (paramIndex, decorator) {
    return function (target, key) { decorator(target, key, paramIndex); }
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.RgbToHexController = void 0;
const common_1 = require("@nestjs/common");
const RgbToHex_service_1 = require("../service/RgbToHex.service");
const RgbToHexRequest_1 = require("../dto/request/RgbToHexRequest");
let RgbToHexController = class RgbToHexController {
    rgbToHexService;
    constructor(rgbToHexService) {
        this.rgbToHexService = rgbToHexService;
    }
    transfer(rgbToHexRequest) {
        return this.rgbToHexService.transfer(rgbToHexRequest.r, rgbToHexRequest.g, rgbToHexRequest.b);
    }
};
exports.RgbToHexController = RgbToHexController;
__decorate([
    (0, common_1.Post)('/rgb'),
    __param(0, (0, common_1.Body)()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [RgbToHexRequest_1.RGBToHexRequest]),
    __metadata("design:returntype", void 0)
], RgbToHexController.prototype, "transfer", null);
exports.RgbToHexController = RgbToHexController = __decorate([
    (0, common_1.Controller)(),
    __metadata("design:paramtypes", [RgbToHex_service_1.RgbToHexService])
], RgbToHexController);
//# sourceMappingURL=RgbToHex.controller.js.map