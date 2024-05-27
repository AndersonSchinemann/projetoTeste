var app = angular.module('myLogin', ['ng']);

app.controller('LoginController', function($scope, $http, $window) {
    $scope.login = {};

    $scope.acessoLogin = function() {
		
       // $http.post('/process?username=' + $scope.login.username + '&password=' + $scope.login.password)
        $http.post('/process',$scope.login)
            .then(function(response) {
                if (response.status === 200) {
                    if ($scope.login.email != undefined && $scope.login.password != undefined) {
                        // Extraia o token da resposta
                        alert('Usuario Liberado.');
                        var token = response.data.token;
						alert(token);
                        // Armazene o token no localStorage
                        localStorage.setItem('authToken', token);

                        // Redirecione para a página protegida
                        $window.location.href = '/dashboard';
                    } else {
                        // Tratar erro inesperado no Content-Type
                        alert('Houve um problema: Login ou senha incorreta .'+ $scope.login.password);
                        console.error('Erro: Tipo de conteúdo inesperado na resposta:', response.headers('Content-Type'));
                    }
                } else {
                    // Tratar erro de autenticação ou outros erros
                    alert('Erro no login: ' + response.status);
                    console.error('Erro no login:', response.status);
                }
            })
            .catch(function(error) {
               alert('Erro ao processar a solicitação: ' + $scope.login.password + ' Status '+ error.status);
            });
    };
});
