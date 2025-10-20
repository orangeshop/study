import { RgbToHexService } from '../service/RgbToHex.service';
import { RGBToHexRequest } from '../dto/request/RgbToHexRequest';
export declare class RgbToHexController {
    private readonly rgbToHexService;
    constructor(rgbToHexService: RgbToHexService);
    transfer(rgbToHexRequest: RGBToHexRequest): string;
}
