import { ExceptionFilter, ArgumentsHost } from '@nestjs/common';
import { HexErrorResponse } from './HexErrorResponse';
export declare class HexErrorFilter implements ExceptionFilter {
    catch(exception: HexErrorResponse, host: ArgumentsHost): void;
}
