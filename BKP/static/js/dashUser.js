var app = angular.module('dash', []);

app.controller('DashboardController', function($scope, $http) {
    $scope.showNotifications = false;
    $scope.showUserMenu = false;
	$scope.notificationCount = 4; // Inicialmente, não há notificações
	
	$scope.changePage = function() {
        $http.get('/dashboard/detalhe')
            .then(function(response) {
                $scope.currentPage = response.data.plainText;
                alert($scope.currentPage);
            })
            .catch(function(error) {
                console.error('Erro ao obter o texto:', error);
            });
      };
     $scope.listagem = function() {
       
        $http.get('/dashboard/listagem')
            .then(function(response) {
                $scope.texto = response.data.texto;
                 alert("listagem");
            })
            .catch(function(error) {
                console.error('Erro ao obter o texto:', error);
            });
    };
    // Função para adicionar uma nova notificação
    $scope.addNotification = function() {
        $scope.notificationCount++; // Incrementa o contador
    };
    $scope.toggleNotifications = function() {
        $scope.showNotifications = !$scope.showNotifications;
        $scope.showUserMenu = false; // Oculta o menu do usuário, se estiver aberto
    };

    $scope.toggleUserMenu = function() {
        $scope.showUserMenu = !$scope.showUserMenu;
        $scope.showNotifications = false; // Oculta as notificações, se estiverem abertas
    };
	 // Função para redefinir as variáveis quando ocorrer um clique no documento
    function resetMenus(event) {
        var clickedElement = event.target;

        // Verificar se o elemento clicado não é o menu de notificações nem o menu do usuário
        var isNotificationMenu = clickedElement.closest('.notifications');
        var isUserMenu = clickedElement.closest('.user');

        if (!isNotificationMenu && !isUserMenu) {
            $scope.showNotifications = false;
            $scope.showUserMenu = false;
            $scope.$apply(); // Atualizar o escopo do AngularJS
        }
    }

    // Adicionar um ouvinte de evento de clique no documento inteiro
    document.addEventListener('click', resetMenus);
});