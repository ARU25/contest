'use strict';

// Declare app level module which depends on views, and core components
angular.module('contest', [
  'ngRoute',
  'ngResource',
  'ngFileUpload'
]).
config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  $locationProvider.hashPrefix('!');

  $routeProvider.when('/home', {
            templateUrl: 'view/home.html',
            controller: 'HomeCtrl'
          }).when('/essay', {
              templateUrl: 'view/essay.html',
              controller: 'RegisterationController',
              controllerAs: 'ctrl'
          }).when('/result', {
              templateUrl: 'view/results.html',
              controller: 'ResultController',
              controllerAs: 'ctrl'
          }).when('/', {
              templateUrl: 'view/home.html',
              controller: 'HomeCtrl'
          });

}])
.factory('resultService',['$resource',function($resource){
    return $resource('/api/result');
}])
.controller('HomeCtrl',function(){

})
.controller('ResultController',['resultService', function(resultService){

    var _this = this;

    resultService.query().$promise.then(function(response){
        _this.results = response;
    });

}])
.controller('RegisterationController',['Upload', function(Upload){

  this.contestant={};

  this.saveContestant = function(contestant){

      Upload.upload({
          url: 'api/contestant',
          data: {
              file: contestant.file,
              name: contestant.name,
              email: contestant.email,
              phoneNo: contestant.phoneNo
          }
      }).then(function (resp) {
          alert('Registration Successful');
          console.log('Success ' + resp.config.data.file.name + 'uploaded. Response: ' + resp.data);
      }, function (resp) {
          alert('Error during registration');
          console.log('Error status: ' + resp.status);
      }, function (evt) {
          var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
          console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
      });

  };

}]);
