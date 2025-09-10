import Header from "../../components/Header";
import FoodList from"../../components/Food";
import Footer from "../../components/Footer";
import {getProducts} from"../../services/Products"
import { useEffect, useState } from "react";

export default function Home() {
   const [foods, setFoods] = useState([]);
    useEffect(() => {
    // gọi API lấy page 1
    getProducts(1)
      .then((data) => setFoods(data))
      .catch((err) => console.error("Lỗi khi load sản phẩm:", err));
  }, []);
//   const foods = [
//   {
//     id: 1,
//     name: "Pizza Hải Sản",
//     detail: "Pizza với hải sản tươi ngon, sốt đặc biệt.",
//     img: "/images/f1.png",
//     price: 17,
//   },
//   {
//     id: 2,
//     name: "Pizza Phô Mai",
//     detail: "Ngập tràn phô mai Mozzarella béo ngậy.",
//     img: "/images/f2.png",
//     price: 15,
//   },
//   {
//     id: 3,
//     name: "Pizza Bò BBQ",
//     detail: "Thịt bò nướng BBQ thơm lừng.",
//     img: "/images/f3.png",
//     price: 18,
//   },
//   {
//     id: 4,
//     name: "Pizza Gà Nướng",
//     detail: "Gà nướng sốt cay đậm đà.",
//     img: "/images/f4.png",
//     price: 16,
//   },
//   {
//     id: 5,
//     name: "Pizza Rau Củ",
//     detail: "Tươi mát với nhiều loại rau củ.",
//     img: "/images/f5.png",
//     price: 14,
//   },
//   {
//     id: 6,
//     name: "Pizza Xúc Xích",
//     detail: "Xúc xích Đức thơm ngon, giòn rụm.",
//     img: "/images/f6.png",
//     price: 15,
//   },
//   {
//     id: 7,
//     name: "Pizza Cá Ngừ",
//     detail: "Độc đáo với cá ngừ và phô mai.",
//     img: "/images/f7.png",
//     price: 17,
//   },
//   {
//     id: 8,
//     name: "Pizza Trứng",
//     detail: "Lạ miệng với trứng ốp la vàng óng.",
//     img: "/images/f8.png",
//     price: 13,
//   },
//   {
//     id: 9,
//     name: "Pizza Thập Cẩm",
//     detail: "Kết hợp đủ loại topping hấp dẫn.",
//     img: "/images/f9.png",
//     price: 19,
//   },
// ];


  return (
    <>
      <div className="hero_area">
        <div className="bg-box">
          <img src="/images/hero-bg.jpg" alt="" />
        </div> <Header />
        <section className="slider_section ">
          <div id="customCarousel1" className="carousel slide" data-ride="carousel">
            <div className="carousel-inner">
              <div className="carousel-item active">
                <div className="container ">
                  <div className="row">
                    <div className="col-md-7 col-lg-6 ">
                      <div className="detail-box">
                        <h1>
                          Fast Food Restaurant
                        </h1>
                        <p>
                          Doloremque, itaque aperiam facilis rerum, commodi, temporibus sapiente ad mollitia laborum quam
                          quisquam esse error unde. Tempora ex doloremque, labore, sunt repellat dolore, iste magni quos
                          nihil ducimus libero ipsam.
                        </p>
                        <div className="btn-box">
                          <a href="" className="btn1">
                            Order Now
                          </a>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div className="carousel-item ">
                <div className="container ">
                  <div className="row">
                    <div className="col-md-7 col-lg-6 ">
                      <div className="detail-box">
                        <h1>
                          Fast Food Restaurant
                        </h1>
                        <p>
                          Doloremque, itaque aperiam facilis rerum, commodi, temporibus sapiente ad mollitia laborum quam
                          quisquam esse error unde. Tempora ex doloremque, labore, sunt repellat dolore, iste magni quos
                          nihil ducimus libero ipsam.
                        </p>
                        <div className="btn-box">
                          <a href="" className="btn1">
                            Order Now
                          </a>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div className="carousel-item">
                <div className="container ">
                  <div className="row">
                    <div className="col-md-7 col-lg-6 ">
                      <div className="detail-box">
                        <h1>
                          Fast Food Restaurant
                        </h1>
                        <p>
                          Doloremque, itaque aperiam facilis rerum, commodi, temporibus sapiente ad mollitia laborum quam
                          quisquam esse error unde. Tempora ex doloremque, labore, sunt repellat dolore, iste magni quos
                          nihil ducimus libero ipsam.
                        </p>
                        <div className="btn-box">
                          <a href="" className="btn1">
                            Order Now
                          </a>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div className="container">
              <ol className="carousel-indicators">
                <li data-target="#customCarousel1" data-slide-to="0" className="active"></li>
                <li data-target="#customCarousel1" data-slide-to="1"></li>
                <li data-target="#customCarousel1" data-slide-to="2"></li>
              </ol>
            </div>
          </div>

        </section>

      </div>

      <section class="food_section layout_padding-bottom">
  <div class="container">
    <div class="heading_container heading_center">
      <h2>
        Our Menu
      </h2>
    </div>

    <ul class="filters_menu">
      <li class="active" data-filter="*">All</li>
      <li data-filter=".burger">Burger</li>
      <li data-filter=".pizza">Pizza</li>
      <li data-filter=".pasta">Pasta</li>
      <li data-filter=".fries">Fries</li>
    </ul>

    <div class="filters-content">
      <div class="row grid">
   <FoodList foods={foods}></FoodList>
      </div>
    </div>
  </div>
</section>

      
      <Footer />
    </>
  );
}
