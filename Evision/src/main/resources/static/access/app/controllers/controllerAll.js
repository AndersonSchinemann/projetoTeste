angular.module('appAll',['authService'])

  .controller('LoginController', ['$scope', '$location', 'authService', function($scope, $location, AuthService) {
	$scope.login = {};
	     $scope.acessoLogin = function() {
		     
	        AuthService.login($scope.login)
	        .then(function() {
			  alert('Usuario Liberado.AuthService ');
	          $location.path('/dashboard');
	        })
	        .catch(function(error) {
		      alert('Usuario não encontrado AuthService ');
	          $scope.errorMessage = "Erro ao fazer login. Verifique suas credenciais. AuthService";
	          console.error('Erro ao processar a solicitação: AuthService', error);
	        });
	    };
  }])
  
  .controller('HomeController', ['$scope', 'AuthService', function($scope, AuthService) {
    // Implemente as funções e lógica relacionadas à página inicial aqui
  }])
  .controller('AdminPanelController', ['$scope', 'AuthService', function($scope, AuthService) {
    // Implemente as funções e lógica relacionadas ao painel de administração aqui
  }]);

angular.module('app.services', []) // Defina o módulo 'app.services' e suas dependências aqui
  .factory('AuthService', ['$http', function($http) {
    // Implemente o serviço AuthService aqui
  }]);