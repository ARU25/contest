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
    };


    resultService.query().$promise.then(function(results){
        _this.resultsDeclared = results.length > 0;
        return contestantService.query().$promise;
    }).then(function(response){
            _this.documents = response;
        });


    _this.download = function(fileId){
        documentService.get({"id":fileId});
    }

    _this.rankEntered = function(){
        if(!_this.documents){
            return false;
        }
        return _this.documents.filter(function(document){
           return document.rank;
        }).length > 0;
    }

    _this.validateResult = function(documents){
        if(!documents)
            return false;
        var rankMap = {};
        var isValid = true;
        documents.forEach(function(document){
            if(!document.rank){
                return;
            }
            if(rankMap[document.rank]){
                isValid = false;
            }
            rankMap[document.rank] = document;
        });

        if(!isValid){
            return isValid;
        }

        var ranks = Object.keys(rankMap);
        if (ranks.length == 0){
            return false;
        }
        var sortedRanks = ranks.sort(function(a, b){
           return a>b;
        });

        sortedRanks.forEach(function(element,index){
            if(index-1 >=0){
                isValid = isValid && (parseInt(element) == (parseInt(sortedRanks[index-1]) + 1));
            } else if (parseInt(element) !== 1){
                isValid = false;
            }
        });

        return isValid;

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