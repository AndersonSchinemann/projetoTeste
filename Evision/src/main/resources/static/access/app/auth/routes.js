angular.module('app.routes',[])
  .config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when('/login', {
        templateUrl: 'templates/loginUser.html',
        controller: 'LoginController'
      })
      .when('/dashboard', {
        templateUrl: 'templates/dashboard.html',
        controller: 'DashboardController',
        resolve: {
          auth: ['authGuard', function(authGuard) {
            return authGuard.canActivate().then(function() {
              return RoleGuard.canActivate({ data: { expectedRole: 'admin' } });
            });
          }]
        }
      })
      .when('/admin', {
        templateUrl: 'admin/admin.html',
        controller: 'AdminController',
        resolve: {
          auth: ['AuthGuard', 'RoleGuard', function(AuthGuard, RoleGuard) {
            return AuthGuard.canActivate().then(function() {
              return RoleGuard.canActivate({ data: { expectedRole: 'ROLE_ADMIN' } });
            });
          }]
        }
      })
      .when('/user', {
        templateUrl: 'user/user.html',
        controller: 'UserController',
        resolve: {
          auth: ['AuthGuard', 'RoleGuard', function(AuthGuard, RoleGuard) {
            return AuthGuard.canActivate().then(function() {
              return RoleGuard.canActivate({ data: { expectedRole: 'ROLE_USER' } });
            });
          }]
        }
      })
      .otherwise({
        redirectTo: '/dashboard'
      });
  }]);