var app = angular.module('CadastroApp',  []);


app.controller('UsuarioController', function($scope, $http) {
    $scope.usuario = {};

    $scope.cadastrarUsuario = function() {
        $http.post('/api/usuarios/cad', $scope.usuario)
            .then(function(response) {
                console.log(response.data);
                alert('Usuário cadastrado com sucesso!');
            })
            .catch(function(error) {
                console.log(error);
                alert('Erro ao cadastrar usuário. Por favor, tente novamente.');
            });
    }
});



