var app = angular.module('myLogin', []);

app.controller('LoginController', function($scope, $http, $window) {
    $scope.login = {};

    $scope.acessoLogin = function() {
        $http.post('/authenticate', $scope.login)
            .then(function(response) {
				if (response.status === 200) {
                    var token = response.data;
                    if (token) {
                        alert('Usuario Liberado.');
                        localStorage.setItem('authToken', token);
                        $http.defaults.headers.common['Authorization'] = 'Bearer ' + token;
                        $window.location.href = '/dashboard';
                    } else {
                        alert('Erro na autenticação.');
                        console.error('Erro: token não recebido na resposta da autenticação.');
                    }
                } else {
                    alert('Erro no login: ' + response.status);
                    console.error('Erro no login:', response.status);
                }
            })
            .catch(function(error) {
                alert("Usuario não encontrado");
                console.error('Erro ao processar a solicitação:', error);
            });
    };
});
