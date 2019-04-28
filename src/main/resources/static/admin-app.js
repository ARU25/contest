'use strict';

// Declare app level module which depends on views, and core components
angular.module('contestAdmin', [
  'ngResource'
]).factory('contestantService',['$resource',function($resource){
    return $resource('api/contestant');
}]).factory('resultService',['$resource',function($resource){
    return $resource('api/result');
}]).factory('documentService',['$resource',function($resource){
    return $resource('api/document/:id');
}]).controller('AdminController',['contestantService','documentService','resultService',function(contestantService,documentService,resultService){

    var _this = this;

    function getResults(results) {
        return results.filter(function (result) {
            return result.rank;
        }).map(function (result) {
            return {
                'id': result.id,
                'rank': result.rank
            }
        });
    }

    contestantService.query().$promise.then(function(response){
            _this.documents = response;
        });


    _this.download = function(fileId){
        documentService.get({"id":fileId});
    }

    _this.saveResult = function(documents){
        var results = {
            "resultList": getResults(documents)
        };

        resultService.save(results).$promise.then(function(){
            alert('result saved');
        });
    }



}]);