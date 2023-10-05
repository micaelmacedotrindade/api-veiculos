import React, { Component } from "react";
import { Routes, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import CadastrarVeiculo from "./components/cadastrar-veiculo.component";
import Veiculo from "./components/veiculo.component";
import VeiculosList from "./components/veiculos-list.component";

class App extends Component {
  render() {
    return (
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">         
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link to={"/veiculos"} className="nav-link">
                Veículos
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/cadastrar"} className="nav-link">
                Cadastrar
              </Link>
            </li>
          </div>
        </nav>

        <div className="container mt-3">
          <Routes>
            <Route path="/" element={<VeiculosList/>} />
            <Route path="/veiculos" element={<VeiculosList/>} />
            <Route path="/cadastrar" element={<CadastrarVeiculo/>} />
            <Route path="/veiculos/:id" element={<Veiculo/>} />
          </Routes>
        </div>
      </div>
    );
  }
}

export default App;