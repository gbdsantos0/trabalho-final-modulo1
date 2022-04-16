package com.dbc.pokesuits.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.dbc.pokesuits.dto.mailconnect.EmailUserDTO;
import com.dbc.pokesuits.dto.mailconnect.Operation;
import com.dbc.pokesuits.dto.mailconnect.ValidatedUserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dbc.pokesuits.dto.user.UserCreateDTO;
import com.dbc.pokesuits.dto.user.UserDTO;
import com.dbc.pokesuits.dto.user.UserEditDto;
import com.dbc.pokesuits.entity.RegraEntity;
import com.dbc.pokesuits.entity.UserEntity;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;
import com.dbc.pokesuits.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final RegraService regraService;
	private final RegistrationMailProducerService registrationMailProducerService;

	public UserDTO userLogado(Integer idUser) throws RegraDeNegocioException {
		log.info("Chamado metodo tranformarDto do User");
		UserEntity byId = getById(idUser);
		return new UserDTO(byId.getId(), byId.getNome(), byId.getEmail(), byId.getUsername());
	}

	public Page<UserDTO> listarUsers(Integer pagina) {
		log.info("Chamado metodo ListarUsers;");

		Pageable pageable = PageRequest.of(pagina == null ? 0 : pagina, 10);

		List<UserDTO> collect = userRepository.findAll(pageable).stream()
				.map(user -> new UserDTO(user.getId(), user.getNome(), user.getEmail(), user.getUsername()))
				.collect(Collectors.toList());

		return new PageImpl<>(collect);
	}

	public void criaUser(UserCreateDTO createDTO) throws Exception {
		log.info("Chamado metodo AdicionarUser;");
		
		if(findByUsername(createDTO.getUsername()).isPresent())throw new RegraDeNegocioException("Username já cadastrado");
		
		// buscando grupos
		Set<RegraEntity> regraEntitySet = new HashSet<>();
		regraEntitySet.add(regraService.getById(3));

		// setando usuario
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(createDTO.getUsername());
		userEntity.setEmail(createDTO.getEmail());
		userEntity.setNome(createDTO.getNome());
		userEntity.setPassword(new BCryptPasswordEncoder().encode(createDTO.getPassword()));
		userEntity.setRegras(regraEntitySet);
		userEntity.setActive(false);

		UserEntity userAtualizado = userRepository.save(userEntity);

		EmailUserDTO emailUserDTO = EmailUserDTO.builder()
				.username(userEntity.getUsername())
				.name(userEntity.getNome())
				.email(userEntity.getEmail())
				.operation(Operation.REGISTER)
				.build();

		registrationMailProducerService.sendConfirmationMail(emailUserDTO);
		log.info("Criado o User de ID: " + userAtualizado.getId());
	}

	public void removerUser(int id) throws Exception {
		log.info("Chamado metodo RemoverUser;");
		
		UserEntity userEntity = getById(id);

		userEntity.setActive(false);

		userRepository.save(userEntity);

		EmailUserDTO emailUserDTO = EmailUserDTO.builder()
				.username(userEntity.getUsername())
				.name(userEntity.getNome())
				.email(userEntity.getEmail())
				.operation(Operation.DELETE)
				.build();

		registrationMailProducerService.sendDeleteMail(emailUserDTO);

		log.info("Removido o User de ID: " + id);

	}

	public void activateUser(String username, boolean activate) throws RegraDeNegocioException {
		UserEntity userEntity = findByUsername(username).orElseThrow(()->new RegraDeNegocioException("Usuário não encontrado: "+ username));
		if(activate){
			userEntity.setActive(true);

			userRepository.save(userEntity);
			log.info("Usuário ativo no sistema com o id: " + userEntity.getId());
		}else{
			userRepository.deleteById(userEntity.getId());
			log.info("Usuário removido do sistema com o id: " + userEntity.getId());
		}
	}

	public UserDTO editarUser(UserEditDto editDTO, Integer id) throws RegraDeNegocioException {
		log.info("Chamado metodo editarUser;");

		UserEntity userConvertido = getById(id);
		
		userConvertido.setEmail(editDTO.getEmail());
		userConvertido.setNome(editDTO.getNome());
		
		UserEntity userAtualizado = userRepository.save(userConvertido);
		log.info("Persistido as mudanças no User de ID: " + userAtualizado.getId());

		return new UserDTO(userAtualizado.getId(), userAtualizado.getNome(), userAtualizado.getEmail(), userAtualizado.getUsername());
	}

	public UserEntity getById(Integer idUser) throws RegraDeNegocioException {
		log.info("Chamado metodo getById do User");
		return userRepository.findById(idUser)
				.orElseThrow(() -> new RegraDeNegocioException("O ID do User passadso não Existe"));
	}

	public Optional<UserEntity> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
