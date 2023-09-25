package com.api.veiculos.apiveiculos.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.api.veiculos.apiveiculos.domain.veiculo.Veiculo;
import com.api.veiculos.apiveiculos.domain.veiculo.VeiculoRecord;
import com.api.veiculos.apiveiculos.domain.veiculo.VeiculosRepository;
import com.api.veiculos.apiveiculos.exception.ResourceNotFoundException;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Component
@Path("/api/v1")
public class VeiculosResource {

 @Autowired
 private VeiculosRepository veiculosRepository;

 @GET
 @Produces("application/json")
 @Path("/veiculos")
	public ResponseEntity<List<VeiculoRecord>> listaDadosClientesJuridicos() {

		List<VeiculoRecord> listVeiculos = veiculosRepository.findAll().stream().map(VeiculoRecord::new).toList();

		
		if (listVeiculos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(listVeiculos);
	};

 @GET
 @Produces("application/json")
 @Path("/veiculos/{id}")
 public ResponseEntity<Veiculo> getUserById(@PathParam(value = "id") Long id) throws ResourceNotFoundException {
	 Optional<Veiculo> veiculo = veiculosRepository.findById(id);
    //.orElseThrow(() -> new ResourceNotFoundException("Veiculo não encontrado :: " + id));
	 
	 if(veiculo.isPresent()) {
		 return ResponseEntity.ok().body(veiculo.get());
	 }else {
		 return ResponseEntity.notFound().build();
	 }
	 
	 
 }
//
// @POST
// @Produces("application/json")
// @Consumes("application/json")
// @Path("/users")
// @PostMapping("/users")
// public User createUser(User user) {
//  return userRepository.save(user);
// }
//
// @PUT
// @Consumes("application/json")
// @Path("/users/{id}}")
// public ResponseEntity<User> updateUser(@PathParam(value = "id") Long userId,
//   @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
//  User user = userRepository.findById(userId)
//    .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + userId));
//
//  user.setEmailId(userDetails.getEmailId());
//  user.setLastName(userDetails.getLastName());
//  user.setFirstName(userDetails.getFirstName());
//  final User updatedUser = userRepository.save(user);
//  return ResponseEntity.ok(updatedUser);
// }
//
// @DELETE
// @Path("/users/{id}")
// public Map<String, Boolean> deleteUser(@PathParam(value = "id") Long userId) throws ResourceNotFoundException {
//  User user = userRepository.findById(userId)
//    .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + userId));
//
//  userRepository.delete(user);
//  Map<String, Boolean> response = new HashMap<>();
//  response.put("deleted", Boolean.TRUE);
//  return response;
// }
}