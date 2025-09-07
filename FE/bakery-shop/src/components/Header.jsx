export default function Header() {
  return (
    <div className="container">
      <nav className="navbar navbar-expand-lg custom_nav-container">
        <a className="navbar-brand" href="index.html">
          <span>Feane</span>
        </a>

        <button
          className="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span> </span>
        </button>

        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav mx-auto">
            <li className="nav-item active">
              <a className="nav-link" href="index.html">
                Home <span className="sr-only">(current)</span>
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="menu.html">Menu</a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="about.html">About</a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="book.html">Book Table</a>
            </li>
          </ul>

          <div className="user_option">
            <a href="sign.html" className="user_link">
              <i className="fa fa-user" aria-hidden="true"></i>
            </a>

            <a className="cart_link" href="#">
              {/* SVG giữ nguyên */}
              <svg
                version="1.1"
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 456.029 456.029"
              >
                <g>
                  <g>
                    <path d="M345.6,338.862c-29.184,0-53.248..." />
                  </g>
                </g>
              </svg>
            </a>

            <form className="form-inline">
              <button className="btn my-2 my-sm-0 nav_search-btn" type="submit">
                <i className="fa fa-search" aria-hidden="true"></i>
              </button>
            </form>

            <a href="" className="order_online">
              Order Online
            </a>
          </div>
        </div>
      </nav>
    </div>
  );
}
