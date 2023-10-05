import React, { Component } from "react";
import VeiculosDataService from "../services/veiculos.service";
import { withRouter } from "../common/with-router";
import SelectMultiplo from "./select-multiplo.component"

class Veiculo extends Component {
    marcas = ["Ford", "Hyundai", "Mercedes-Benz", "Volkswagen", "Toyota"];
    constructor(props) {
        super(props);
        this.onChangeVeiculo = this.onChangeVeiculo.bind(this);
        this.onChangeMarca = this.onChangeMarca.bind(this);
        this.onChangeAno = this.onChangeAno.bind(this);
        this.onChangeDescricao = this.onChangeDescricao.bind(this);
        this.onChangeVendido = this.onChangeVendido.bind(this);
        this.getVeiculo = this.getVeiculo.bind(this);
        this.updateVeiculo = this.updateVeiculo.bind(this);
        this.deleteVeiculo = this.deleteVeiculo.bind(this);

        this.state = {
            currentVeiculo: {
                id: null,
                veiculo: "",
                marca: "",
                ano: "",
                descricao: "",
                vendido: false,
                marcas: []
            },
            message: ""
        };
    }

    componentDidMount() {
        this.getVeiculo(this.props.router.params.id);
    }

    onChangeVeiculo(e) {
        const veiculo = e.target.value;

        this.setState(function (prevState) {
            return {
                currentVeiculo: {
                    ...prevState.currentVeiculo,
                    veiculo: veiculo
                }
            };
        });
    }

    onChangeMarca(e) {
        const marca = e.target.value;

        this.setState(prevState => ({
            currentVeiculo: {
                ...prevState.currentVeiculo,
                marca: marca
            }
        }));
    }

    onChangeAno(e) {
        const ano = e.target.value;

        this.setState(prevState => ({
            currentVeiculo: {
                ...prevState.currentVeiculo,
                ano: ano
            }
        }));
    }

    onChangeDescricao(e) {
        const descricao = e.target.value;

        this.setState(prevState => ({
            currentVeiculo: {
                ...prevState.currentVeiculo,
                descricao: descricao
            }
        }));
    }

    onChangeVendido = (e) => {
        const vendido = e.target.value;

        this.setState(prevState => ({
            currentVeiculo: {
                ...prevState.currentVeiculo,
                vendido: vendido
            }
        }));
    }

    getVeiculo(id) {
        VeiculosDataService.get(id)
            .then(response => {
                this.setState({
                    currentVeiculo: response.data.body,
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    updateVeiculo() {
        VeiculosDataService.update(
            this.state.currentVeiculo.id,
            this.state.currentVeiculo
        )
            .then(response => {
                console.log(response.data);
                this.setState({
                    message: "O veiculo foi atualizado com sucesso!"
                });
            })
            .catch(e => {
                console.log(e);
            });
    }

    deleteVeiculo() {
        VeiculosDataService.delete(this.state.currentVeiculo.id)
            .then(response => {
                console.log(response.data);
                this.props.router.navigate('/veiculos');
            })
            .catch(e => {
                console.log(e);
            });
    }


    render() {
        const { currentVeiculo } = this.state;

        return (
            <div>
                {currentVeiculo ? (
                    <div className="edit-form">
                        <h4>Veiculo</h4>
                        <form>
                            <div className="form-group">
                                <label htmlFor="veiculo">Veiculo</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="veiculo"
                                    value={currentVeiculo.veiculo}
                                    onChange={this.onChangeVeiculo}
                                />
                            </div>

                            <div className="form-group">
                                <label htmlFor="marca">Marca</label>
                            <SelectMultiplo
                                id="marca"
                                value={currentVeiculo.marca}
                                onChange={this.onChangeMarca}>
                            </SelectMultiplo>
                            </div>

                            <div className="form-group">
                                <label htmlFor="ano">Ano</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="ano"
                                    value={currentVeiculo.ano}
                                    onChange={this.onChangeAno}
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="descricao">Descrição</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="descricao"
                                    value={currentVeiculo.descricao}
                                    onChange={this.onChangeDescricao}
                                />
                            </div>
                            <div className="form-group">
                                <label>
                                Situação:
                                    <select value={currentVeiculo.vendido} class="btn btn-light m-2" id="vendido_" onChange={this.onChangeVendido}>
                                        <option value={true}>Vendido</option>
                                        <option value={false}>Disponível</option>
                                    </select>
                                </label>
                            </div>

                        </form>

                        <button
                            class="btn btn-danger ml-0 mr-2"
                            onClick={this.deleteVeiculo}
                        >
                            Excluir
                        </button>

                        <button
                            type="submit"
                            class="btn btn-primary m-2"
                            onClick={this.updateVeiculo}
                        >
                            Atualizar
                        </button>
                        <p>{this.state.message}</p>
                    </div>
                ) : (
                    <div>
                        <br />
                        <p>Por favor, clique em um veículo...</p>
                    </div>
                )}
            </div>
        );
    }
}

export default withRouter(Veiculo);
