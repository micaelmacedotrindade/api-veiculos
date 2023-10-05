import React, { Component } from "react";
import VeiculosDataService from "../services/veiculos.service";
import SelectMultiplo from "./select-multiplo.component"

export default class CadastrarVeiculo extends Component {
    marcas = ["Ford", "Hyundai", "Mercedes-Benz", "Volkswagen", "Toyota"];

    constructor(props) {
        super(props);
        this.onChangeVeiculo = this.onChangeVeiculo.bind(this);
        this.onChangeMarca = this.onChangeMarca.bind(this);
        this.onChangeAno = this.onChangeAno.bind(this);
        this.onChangeDescricao = this.onChangeDescricao.bind(this);
        this.onChangeVendido = this.onChangeVendido.bind(this);
        this.salvarVeiculo = this.salvarVeiculo.bind(this);
        this.newVeiculo = this.newVeiculo.bind(this);

        this.state = {
            id: null,
            veiculo: "",
            marca: "",
            ano: "",
            descricao: "",
            vendido: false,

            submitted: false
        };
    }

    onChangeVeiculo(e) {
        this.setState({
            veiculo: e.target.value
        });
    }

    onChangeMarca(e) {
        this.setState({
            marca: e.target.value
        });
    }

    onChangeAno(e) {
        this.setState({
            ano: e.target.value
        });
    }

    onChangeDescricao(e) {
        this.setState({
            descricao: e.target.value
        });
    }

    onChangeVendido(e) {
        this.setState({
            vendido: e.target.value
        });
    }

    salvarVeiculo() {
        var data = {
            veiculo: this.state.veiculo,
            marca: this.state.marca,
            ano: this.state.ano,
            descricao: this.state.descricao,
            vendido: this.state.vendido
        };

        VeiculosDataService.create(data)
            .then(response => {
                this.setState({
                    id: response.data.id,
                    veiculo: response.data.veiculo,
                    marca: response.data.marca,
                    ano: response.data.ano,
                    descricao: response.data.descricao,
                    vendido: response.data.vendido,

                    submitted: true
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    newVeiculo() {
        this.setState({
            id: null,
            veiculo: "",
            marca: "",
            ano: "",
            descricao: "",
            vendido: "false",

            submitted: false
        });
    }

    render() {
        return (
            <div className="submit-form">
                {this.state.submitted ? (
                    <div>
                        <h4>Veiculo cadastrado com sucesso!</h4>
                        <button className="btn btn-success" onClick={this.newVeiculo}>
                            Cadastrar
                        </button>
                    </div>
                ) : (
                    <div>
                        <div className="form-group">
                            <label htmlFor="veiculo">Veiculo</label>
                            <input
                                type="text"
                                className="form-control"
                                id="veiculo"
                                required
                                value={this.state.veiculo}
                                onChange={this.onChangeVeiculo}
                                name="veiculo"
                            />
                        </div>

                        <div className="form-group">
                            <label>
                                Marca:
                            </label>
                            <SelectMultiplo
                                value={this.state.marca}
                                onChange={this.onChangeMarca}>

                            </SelectMultiplo>

                        </div>

                        <div className="form-group">
                            <label htmlFor="ano">Ano</label>
                            <input
                                type="text"
                                className="form-control"
                                id="ano"
                                required
                                value={this.state.ano}
                                onChange={this.onChangeAno}
                                name="ano"
                            />
                        </div>

                        <div className="form-group">
                            <label htmlFor="descricao">Descricao</label>
                            <input
                                type="text"
                                className="form-control"
                                id="descricao"
                                required
                                value={this.state.descricao}
                                onChange={this.onChangeDescricao}
                                name="descricao"
                            />
                        </div>

                        <div className="form-group">
                            <label>
                                Situação:
                                <select value={this.state.vendido} id="vendido_" class="btn btn-light m-2" onChange={this.onChangeVendido}>
                                    <option value={true}>Vendido</option>
                                    <option value={false}>Disponível</option>
                                </select>
                            </label>
                        </div>

                        <button onClick={this.salvarVeiculo} class="btn btn-success">
                            Cadastrar
                        </button>
                    </div>
                )}
            </div>
        );
    }
}
