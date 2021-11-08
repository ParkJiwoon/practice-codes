import {Link, Route} from "react-router-dom";
import Home from "./Home";
import About from "./About";

export default function RouteApp() {
  return (
    <div>
      <ul>
        <li><Link to="/">홈</Link></li>
        <li><Link to="/about">소개</Link></li>
      </ul>
      <hr />
      <Route path="/" component={Home} exact={true} />
      <Route path="/about" component={About} />
    </div>
  )
}