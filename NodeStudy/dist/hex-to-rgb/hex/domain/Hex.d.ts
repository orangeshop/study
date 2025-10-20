export declare class Hex {
    hex: string;
    constructor(hex: string);
    hexToRgb(): {
        r: number;
        g: number;
        b: number;
    };
    toRgb: (v: string, start: number, end: number) => number;
}
