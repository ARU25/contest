'use strict';

// Declare app level module which depends on views, and core components
angular.module('contestAdmin', [
  'ngResource'
]).factory('contestantService',['$resource',function($resource){
    return $resource('api/contestant');
}]).factory('documentService',['$resource',function($resource){
    return $resource('api/document/:id');
}]).controller('AdminController',['contestantService','documentService',function(contestantService,documentService){

    var _this = this;

    contestantService.query().$promise.then(function(response){
            _this.documents = response;
        });

    this.download = function(fileId){
        documentService.get({"id":fileId});
    }



}]);
