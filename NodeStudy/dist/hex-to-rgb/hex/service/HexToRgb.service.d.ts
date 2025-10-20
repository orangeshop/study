import { HexToRgbRequest } from '../dto/request/HexToRgbRequest';
export declare class HexToRgbService {
    transfer(v: HexToRgbRequest): {
        r: number;
        g: number;
        b: number;
    };
}
