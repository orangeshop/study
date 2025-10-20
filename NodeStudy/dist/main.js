"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const core_1 = require("@nestjs/core");
const HexError_filter_1 = require("./common/HexError.filter");
const AppModule_1 = require("./common/AppModule");
async function bootstrap() {
    const app = await core_1.NestFactory.create(AppModule_1.AppModule);
    app.useGlobalFilters(new HexError_filter_1.HexErrorFilter());
    await app.listen(process.env.PORT ?? 3000);
}
bootstrap();
//# sourceMappingURL=main.js.map