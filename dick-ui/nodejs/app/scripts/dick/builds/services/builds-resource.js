'use strict';

angular.module('dick.builds')
    .factory('BuildsResource', ['$resource',
        function ($resource) {
            return $resource('/api/builds/:id/:stage', {id: '@id', stage: '@stage'}, {
                kill: {method: 'POST', url: '/api/builds/:id/kill'},
                getSilently: {method: 'GET', ignoreLoadingBar: true}
                }
            );
        }
    ]);

