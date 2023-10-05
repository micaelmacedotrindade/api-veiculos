import http from "../http-common";

class VeiculosDataService {
  
  getAll(marca, ano) {
    if(marca == null){
      marca = 'all';
    }
    if(ano == null){
      ano = 0;
    }

    return http.get(`/veiculos?marca=${marca}&ano=${ano}`);
  }

  get(id) {
    return http.get(`/veiculos/${id}`);
  }

  create(data) {
    return http.post("/veiculos", data);
  }

  update(id, data) {
    return http.put(`/veiculos/${id}`, data);
  }

  updateVendido(id, data) {
    return http.patch(`/veiculos/${id}`, data);
  }

  delete(id) {
    return http.delete(`/veiculos/${id}`);
  }

  getVeiculosNaoVendidos(){
    return http.get("/veiculos/veiculosNaoVendidos");
  }

  getVeiculosPorDecada(){
    return http.get("/veiculos/veiculosPorDecada");
  }

  getVeiculosPorFabricante(){
    return http.get("/veiculos/veiculosPorFabricante");
  }

  getVeiculosCadastradosNaSemana(){
    return http.get("/veiculos/veiculosCadastradosNaSemana");
  }

}

export default new VeiculosDataService();
