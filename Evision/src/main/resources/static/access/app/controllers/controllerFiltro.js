var app = angular.module('ListarApp', ['ui.bootstrap.modal', 'ui.bootstrap.tpls']);

app.service('usuarioService', function($http,$window) {
	
	this.abrirEdicao = function(usuario) {
        return angular.copy(usuario);
    };
    this.getAllUsuarios = function() {
        return $http.get('/api/usuarios')
            .then(function(response) {
                return response.data;
            })
            .catch(function(error) {
                console.log('Erro ao obter usuários:', error);
                alert("Erro ao obter usuários:");
                return [];
            });
    };

   this.deleteUsuario = function(usuario) {
    return $http.delete('/api/usuarios/' + usuario.id)
            .then(function() {
                alert("Usuário excluído com sucesso!");
                // Atualiza a lista de usuários após a exclusão
                return $scope.loadUsuarios();
                //return usuarioService.getAllUsuarios();
            })
            .catch(function(error) {
				console.log('Erro ao Deletar usuários:', error);
                //alert("Erro ao excluir usuário. Detalhes do erro: " + JSON.stringify(error));
                // Retorna uma promise rejeitada para que a cadeia de promessas não seja quebrada
                return Promise.reject(error);
            });
    };
    this.pesquisaUsuarios = function(searchText) {
        return $http.get('/api/usuarios/pesquisa', { params: { searchText: searchText } })
            .then(function(response) {
				//$scope.usuarios = response.data;
                return response.data;
            })
            .catch(function(error) {
                console.log('Erro ao pesquisar usuários:', error);
                return [];
            });
    };
    this.atualizarUsuario = function(usuarioAtualizado) {
    // Aqui, você pode adicionar lógica adicional, se necessário, antes de chamar o serviço
    alert("Atualizando usuário...");

    return $http.put('/api/usuarios/' + usuarioAtualizado.id, usuarioAtualizado)
        .then(function(response) {
            // Sucesso na atualização
            alert("Usuário atualizado com sucesso!");
            // Lógica adicional, se necessário
            return response.data; // Você pode retornar dados adicionais, se necessário
        })
        .catch(function(error) {
            // Tratamento de erro
            alert("Erro ao atualizar usuário. Detalhes: " + JSON.stringify(error));
            // Você pode lançar uma exceção ou retornar uma mensagem de erro, conforme necessário
            throw error;
        });
	};
	this.sair = function() {
		$http.post('/api/usuarios/logout')  // Endpoint de logout no back-end
            .then(function(response) {
				console.log(response);
                //alert('Logout bem-sucedido!'); 
               $window.location.href = "/";            
            })
            .catch(function(error) {
                console.log(error);
                alert('Erro ao tentar o logout. Tente mais tarde');
            });
	};
});

app.filter('slice', function() {
    return function(arr, start, end) {
        return arr.slice(start, end);
    };
});

app.controller('UsuarioController',function ($scope, usuarioService) {
    $scope.usuarios = [];
    $scope.currentPage = 1;
    $scope.pageSize = 5;
    $scope.totalPages = 0;
    //$scope.searchText ="";
	
    $scope.loadUsuarios = function() {
       
        usuarioService.getAllUsuarios()
            .then(function(usuarios) {
                $scope.usuarios = usuarios;
                $scope.totalPages = Math.ceil($scope.usuarios.length / $scope.pageSize);
                $scope.setPage(1); // Defina a página para 1 ao carregar os usuários
            })
            .catch(function(error) {
                console.log('Erro ao carregar usuários:', error);
            });
    };

    $scope.loadUsuarios();
    $scope.sair = function () {
		//alert("saindo");
		usuarioService.sair();
	}
    
   $scope.abrirEdicao = function(usuario) {
        $scope.usuarioEditavel = usuarioService.abrirEdicao(usuario);
        $('#edicaoUsuarioModal').modal('show');
    };
    $scope.setPage = function (page) {
        $scope.currentPage = page;
    };

    $scope.prevPage = function () {
        if ($scope.currentPage > 1) {
            $scope.currentPage--;
        }
    };

    $scope.nextPage = function () {
        if ($scope.currentPage < $scope.totalPages) {
            $scope.currentPage++;
        }
    };

    $scope.$watch('usuarios', function () {
        $scope.totalPages = Math.ceil($scope.usuarios.length / $scope.pageSize);
        if ($scope.currentPage > $scope.totalPages) {
            $scope.currentPage = $scope.totalPages;
        }
    });

    $scope.pesquisaUsuario = function () {
		alert("Pesquisa feita");
		usuarioService.pesquisaUsuarios($scope.searchText)
        .then(function(usuarios) {
            $scope.usuarios = usuarios; // Atualiza a lista de usuários
        });
    };
    $scope.deleteUsuario = function(usuario) {
        usuarioService.deleteUsuario(usuario);
    };
    $scope.atualizarUsuario = function(usuario) {
    // Supondo que $scope.usuarioEditavel contém os dados atualizados do usuário
    	var usuarioAtualizado = angular.copy(usuario);
		//alert(usuarioAtualizado);
    	usuarioService.atualizarUsuario(usuarioAtualizado)
       	$scope.loadUsuarios();
	};
	$scope.atualizarPainel= function(){
		alert("Atualizando painel");
		$scope.loadUsuarios();
	};
	
	
});


