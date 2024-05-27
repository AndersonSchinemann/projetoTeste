angular.module('authService', [])
.factory('authService', ['$http', '$location', function($http, $location) {
  
  var authTokenKey = 'authToken';
  var userRoleKey = 'usuario';

  return {
    login: function(credentials) {
      return $http.post('/authenticate', credentials).then(function(response) {
        localStorage.setItem(authTokenKey, response.data.token);
        var payload = JSON.parse(atob(response.data.token.split('.')[1]));
        localStorage.setItem(userRoleKey, payload.scope);
        console.log('authTokenKey:', response.data.token);
        console.log('Payload:', payload);
        return response.data;
      });
    },

    logout: function() {
      localStorage.removeItem(authTokenKey);
      localStorage.removeItem(userRoleKey);
      $location.path('/login');
    },

    getToken: function() {
      return localStorage.getItem(authTokenKey);
    },

    getUserRole: function() {
	  console.log('userRoleKey:', userRoleKey);
      return localStorage.getItem(userRoleKey);
    },

    isAuthenticated: function() {
      return !!this.getToken();
    },

    hasRole: function(role) {
      return this.getUserRole() === role;
    }
  };
}]);


