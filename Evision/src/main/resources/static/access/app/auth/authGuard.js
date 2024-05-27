angular.module('authGuard00', ['authService'])
  .factory('authGuard', ['$location', 'authService', function($location, authService) {
    return {
      canActivate: function() {
        if (authService.isAuthenticated()) {
          console.log("Autenticado authGuard");
          return true;
        } else {
          $location.path('/login').search({ returnUrl: $location.path() });
          return false;
        }
      }
    };
  }])
  .factory('RoleGuard', ['$location', 'authService', function($location, authService) {
    return {
      canActivate: function(route) {
        var expectedRole = route.data.expectedRole;
        var userRole = authService.getUserRole();
        if (authService.isAuthenticated() && userRole === expectedRole) {
          console.log("Autenticado RoleGuard");
          return true;
        } else {
          $location.path('/unauthorized');
          return false;
        }
      }
    };
  }]);
