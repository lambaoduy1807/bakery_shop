export default class Product {
  constructor({
    id = null,
    name = '',
    price = 0,
    image = '',
    description = '',
    category = '',
    save=0,
    rate=0,
  } = {}) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.image = image;
    this.description = description;
    this.category = category;
    this.rate=rate;
    this.save=save;
  }
}