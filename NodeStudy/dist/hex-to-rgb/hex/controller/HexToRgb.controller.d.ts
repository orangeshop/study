import { HexToRgbService } from '../service/HexToRgb.service';
import { HexToRgbRequest } from '../dto/request/HexToRgbRequest';
export declare class HexToRgbController {
    private readonly hexToRgbService;
    constructor(hexToRgbService: HexToRgbService);
    transfer(hexToRgbRequest: HexToRgbRequest): {
        r: number;
        g: number;
        b: number;
    };
}
