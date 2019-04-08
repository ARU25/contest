'use strict';

// Declare app level module which depends on views, and core components
angular.module('contestAdmin', [
  'ngResource'
]).factory('documentService',['$resource',function($resource){
    return $resource('api/document');
}]).controller('AdminController',['documentService',function(documentService){

    var _this = this;

        documentService.query().$promise.then(function(response){
            _this.documents = response;
        });

}]);
