export class HexErrorResponse extends Error {
  constructor(message: string) {
    super(message);
    this.name = 'HexErrorResponse';
    Object.setPrototypeOf(this, HexErrorResponse.prototype);
  }
}
