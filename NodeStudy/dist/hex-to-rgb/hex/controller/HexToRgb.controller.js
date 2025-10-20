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
exports.HexToRgbController = void 0;
const common_1 = require("@nestjs/common");
const HexToRgb_service_1 = require("../service/HexToRgb.service");
const HexToRgbRequest_1 = require("../dto/request/HexToRgbRequest");
let HexToRgbController = class HexToRgbController {
    hexToRgbService;
    constructor(hexToRgbService) {
        this.hexToRgbService = hexToRgbService;
    }
    transfer(hexToRgbRequest) {
        return this.hexToRgbService.transfer(hexToRgbRequest);
    }
};
exports.HexToRgbController = HexToRgbController;
__decorate([
    (0, common_1.Post)('/hex'),
    __param(0, (0, common_1.Body)()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [HexToRgbRequest_1.HexToRgbRequest]),
    __metadata("design:returntype", Object)
], HexToRgbController.prototype, "transfer", null);
exports.HexToRgbController = HexToRgbController = __decorate([
    (0, common_1.Controller)(),
    __metadata("design:paramtypes", [HexToRgb_service_1.HexToRgbService])
], HexToRgbController);
//# sourceMappingURL=HexToRgb.controller.js.map