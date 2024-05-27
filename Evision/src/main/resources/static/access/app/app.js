angular.module('app', ['appAll','authGuard00'])
  .config(['$locationProvider', function ($locationProvider) {
    $locationProvider.html5Mode(true);
    $locationProvider.hashPrefix('');
  }]);