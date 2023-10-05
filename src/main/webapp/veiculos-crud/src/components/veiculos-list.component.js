import React, { Component } from "react";
import VeiculosDataService from "../services/veiculos.service";
import { Link } from "react-router-dom";
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import Typography from "@material-ui/core/Typography";
import Button from "@material-ui/core/Button";
import CardActions from "@material-ui/core/CardActions";

export default class VeiculosList extends Component {
    constructor(props) {
        super(props);
        this.onChangeSearchVeiculo = this.onChangeSearchVeiculo.bind(this);
        this.retrieveVeiculos = this.retrieveVeiculos.bind(this);
        this.refreshList = this.refreshList.bind(this);
        this.setActiveVeiculo = this.setActiveVeiculo.bind(this);
        this.searchVeiculo = this.searchVeiculo.bind(this);
        this.onChangeMarcaFiltro = this.onChangeMarcaFiltro.bind(this);
        this.onChangeAnoFiltro = this.onChangeAnoFiltro.bind(this);

        this.state = {
            veiculos: [],
            currentVeiculo: null,
            marcaFiltro: null,
            anoFiltro: null,
            currentIndex: -1,
            searchVeiculo: "",
            naoVendidos: 0,
            veiculosPorDecada: [],
            veiculosPorFabricante: [],
            veiculosCadastradosNaSemana: 0
        };
    }

    componentDidMount() {
        this.retrieveVeiculos();
    }

    onChangeSearchVeiculo(e) {
        const searchVeiculo = e.target.value;

        this.setState({
            searchVeiculo: searchVeiculo
        });
    }

    onChangeVendido(id) {
        VeiculosDataService.updateVendido(
            id
        )
            .then(response => {
                console.log(response.data);
                this.setState({
                    message: "O veiculo foi atualizado com sucesso!",
                    currentVeiculo: response.data.body
                });
            })
            .catch(e => {
                console.log(e);
            });
    }



    retrieveVeiculos() {
        VeiculosDataService.getAll(null, null)
            .then(response => {
                this.setState({
                    veiculos: response.data.body
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });

        VeiculosDataService.getVeiculosNaoVendidos()
            .then(response => {
                this.setState({
                    naoVendidos: response.data.body
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });

        VeiculosDataService.getVeiculosPorDecada()
            .then(response => {
                this.setState({
                    veiculosPorDecada: response.data.body
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });

        VeiculosDataService.getVeiculosCadastradosNaSemana()
            .then(response => {
                this.setState({
                    veiculosCadastradosNaSemana: response.data.body
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });

        VeiculosDataService.getVeiculosPorFabricante()
            .then(response => {
                this.setState({
                    veiculosPorFabricante: response.data.body
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    }

    refreshList() {
        this.retrieveVeiculos();
        this.setState({
            currentVeiculo: null,
            currentIndex: -1
        });
    }

    setActiveVeiculo(veiculo, index) {
        this.setState({
            currentVeiculo: veiculo,
            currentIndex: index
        });
    }

    searchVeiculo() {
        VeiculosDataService.getAll(this.state.marcaFiltro, this.state.anoFiltro)
            .then(response => {
                this.setState({
                    veiculos: response.data.body
                });
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });

    }

    onChangeMarcaFiltro(e) {
        const marca = e.target.value;

        this.setState({
            marcaFiltro: marca
        });
    }

    onChangeAnoFiltro(e) {
        const ano = e.target.value;

        this.setState(prevState => ({
            ...prevState,
            anoFiltro: ano
        }));
    }

    render() {

        const { searchVeiculo, veiculosCadastradosNaSemana, veiculosPorFabricante, veiculosPorDecada, veiculos, currentVeiculo, currentIndex, marcaFiltro, anoFiltro, naoVendidos } = this.state;

        return (
            <div className="list row">
                <div>
                    <Card className="col-md-10">
                        <CardContent>
                            <Typography variant="h6"
                                component="h2"
                                style={{
                                    textAlign: "center"
                                }}>
                                Veículos não vendidos (Disponíveis)
                            </Typography>
                            <Typography
                                style={{
                                    marginBottom: 12,
                                    textAlign: "center"
                                }}
                                color="textSecondary">
                                {naoVendidos}
                            </Typography>
                        </CardContent>
                    </Card>

                    <Card className="col-md-10">
                        <CardContent>
                            <Typography variant="h6"
                                component="h2"
                                style={{
                                    textAlign: "center"
                                }}>
                                Veículos por década de fabricação
                            </Typography>
                            <Typography
                                style={{
                                    marginBottom: 12,
                                    textAlign: "center"
                                }}
                                color="textSecondary">
                                {veiculosPorDecada.map((obj, index) => (
                                    <p>{obj.decada} : {obj.quantidade}</p>
                                ))}
                            </Typography>
                        </CardContent>
                    </Card>

                    <Card className="col-md-10">
                        <CardContent>
                            <Typography variant="h6"
                                component="h2"
                                style={{
                                    textAlign: "center"
                                }}>
                                Veículos por fabricante
                            </Typography>
                            <Typography
                                style={{
                                    marginBottom: 12,
                                    textAlign: "center"
                                }}
                                color="textSecondary">
                                {veiculosPorFabricante.map((fabricante, index) => (
                                    <p>{fabricante.marca} : {fabricante.quantidade}</p>
                                ))}
                            </Typography>
                        </CardContent>
                    </Card>

                    <Card className="col-md-10">
                        <CardContent>
                            <Typography variant="h6"
                                component="h2"
                                style={{
                                    textAlign: "center"
                                }}>
                                Veículos cadastrados na última semana
                            </Typography>
                            <Typography
                                style={{
                                    marginBottom: 12,
                                    textAlign: "center"
                                }}
                                color="textSecondary">
                                {veiculosCadastradosNaSemana}
                            </Typography>
                        </CardContent>
                    </Card>

                </div>
                <div className="col-md-10">
                    <div>
                        <div className="form-group">
                            <label htmlFor="marca">Marca</label>
                            <input
                                type="text"
                                className="form-control"
                                id="marca"
                                value={marcaFiltro}
                                onChange={this.onChangeMarcaFiltro}
                            />
                        </div>

                        <div className="form-group">
                            <label htmlFor="ano">Ano</label>
                            <input
                                type="number"
                                className="form-control"
                                id="ano"
                                value={anoFiltro}
                                onChange={this.onChangeAnoFiltro}
                            />
                        </div>
                        <div className="form-group">
                            <button
                                className="btn btn-outline-secondary"
                                type="button"
                                onClick={this.searchVeiculo}
                            >
                                Search
                            </button>
                        </div>
                    </div>
                </div>

                <div className="col-md-6">
                    <h4>Veículos Cadastrados</h4>

                    <table class="table">
                        <thead class="thead-light">
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Veiculo</th>
                                <th scope="col">Marca</th>
                                <th scope="col">Ano</th>
                            </tr>
                        </thead>
                        <tbody>
                            {veiculos &&
                                veiculos.map((veiculo, index) => (
                                    <tr className={
                                        (index === currentIndex ? "table-active" : "")
                                    }
                                        onClick={() => this.setActiveVeiculo(veiculo, index)}
                                        key={index}>
                                        <th scope="row">{index}</th>
                                        <td>{veiculo.veiculo}</td>
                                        <td>{veiculo.marca}</td>
                                        <td>{veiculo.ano}</td>
                                    </tr>
                                ))}
                        </tbody>
                    </table>

                </div>
                <div className="col-md-6">
                    {currentVeiculo ? (
                        <div>
                            <h4>Veículo</h4>
                            <div>
                                <label>
                                    <strong>Veiculo:</strong>
                                </label>{" "}
                                {currentVeiculo.veiculo}
                            </div>
                            <div>
                                <label>
                                    <strong>Marca:</strong>
                                </label>{" "}
                                {currentVeiculo.marca}
                            </div>
                            <div>
                                <label>
                                    <strong>Ano:</strong>
                                </label>{" "}
                                {currentVeiculo.ano}
                            </div>
                            <div>
                                <label>
                                    <strong>Descrição:</strong>
                                </label>{" "}
                                {currentVeiculo.descricao}
                            </div>
                            <div>
                                <label>
                                    <strong>Vendido:</strong>
                                </label>{" "}
                                {currentVeiculo.vendido ? "Vendido" : "Disponível"}
                            </div>

                            <Link
                                class="btn btn-danger ml-0 mr-2"
                                to={"/veiculos/" + currentVeiculo.id}
                            >
                                Editar
                            </Link>
                            <button
                                class="btn btn-primary m-2"
                                onClick={() => { this.onChangeVendido(currentVeiculo.id) }}>
                                Mudar Status
                            </button>
                            <p>{this.state.message}</p>
                        </div>
                    ) : (

                        <div class="alert alert-info col-md-5">
                            <br />
                            <p>Clique em um veículo...</p>
                        </div>
                    )}
                </div>
            </div>
        );
    }
}
