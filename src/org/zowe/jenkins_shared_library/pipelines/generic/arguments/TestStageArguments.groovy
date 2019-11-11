/*
 * This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-v20.html
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Copyright Contributors to the Zowe Project.
 */

package org.zowe.jenkins_shared_library.pipelines.generic.arguments

import org.zowe.jenkins_shared_library.pipelines.base.enums.ResultEnum
import org.zowe.jenkins_shared_library.pipelines.generic.models.TestReport

/**
 * Represents the arguments available to the
 * {@link org.zowe.jenkins_shared_library.pipelines.generic.GenericPipeline#testGeneric(java.util.Map)} method.
 */
class TestStageArguments extends GenericStageArguments {
    /**
     * Minimum build health needed for this stage to execute.
     *
     * <p>If the current build health is less than the value specified, the stage will be skipped.</p>
     *
     * <p>For more information about the skip precedent, see
     * {@link jenkins_shared_library.pipelines.base.Pipeline#createStage(jenkins_shared_library.pipelines.base.arguments.StageArguments)}</p>
     *
     * @Note Default the resultThreshold to unstable for tests, if a custom value is passed then that will be used instead.
     *
     * @default {@link ResultEnum#UNSTABLE}
     */
    ResultEnum resultThreshold = ResultEnum.UNSTABLE

    /**
     * The location of the generated junit output.
     *
     * <p>This report is required by the test stage. The junit file is used to integrate with
     * Jenkins and mark builds as unstable/failed depending on the test passing status.</p>
     */
    String junit

    /**
     * If we allow the test stage to bypass junit file check.
     *
     * <p>JUnit file is required to determine if test stage is successful or not, and later stages
     * may rely on the test status.</p>
     *
     * <p>This option allows the pipeline doesn't supply junit file for this test.</p>
     */
    Boolean allowMissingJunit = false

    /**
     * Default values provided to cobertura.
     *
     * <p>This map will be merged with {@link #cobertura}, preferring cobertura, as the final
     * object passed to the cobertura plugin</p>
     *
     * @default
     * <pre>
     * {@code
     * [
     *     autoUpdateStability       : true,
     *     classCoverageTargets      : '85, 80, 75',
     *     conditionalCoverageTargets: '70, 65, 60',
     *     failUnhealthy             : false,
     *     failUnstable              : false,
     *     fileCoverageTargets       : '100, 95, 90',
     *     lineCoverageTargets       : '80, 70, 50',
     *     maxNumberOfBuilds         : 20,
     *     methodCoverageTargets     : '80, 70, 50',
     *     onlyStable                : false,
     *     sourceEncoding            : 'ASCII',
     *     zoomCoverageChart         : false
     * ]
     * }
     * </pre>
     */
    public static final Map coberturaDefaults = [
            sourceEncoding            : 'ASCII',
            failUnhealthy             : false,
            failUnstable              : false,
            onlyStable                : false,
            autoUpdateHealth          : false,
            autoUpdateStability       : true,
            fileCoverageTargets       : '100, 95, 90',
            classCoverageTargets      : '85, 80, 75',
            methodCoverageTargets     : '80, 70, 50',
            lineCoverageTargets       : '80, 70, 50',
            conditionalCoverageTargets: '70, 65, 60',
            maxNumberOfBuilds         : 20,
            zoomCoverageChart         : false,
    ]

    /**
     * Cobertura report information.
     *
     * <p>Providing this property causes the test stage to capture a cobertura report. The values
     * provided to this map are directly sent to the cobertura plugin. For information about what
     * map options are acceptable, see <a href="https://jenkins.io/doc/pipeline/steps/cobertura/">
     * Jenkins Cobertura Plugin Documentation</a>.</p>
     */
    Map cobertura

    /**
     * Storage location for any HTML reports generated by your test task.
     *
     * For example, jUnit, or covertura coverage report, etc.
     */
    List<TestReport> htmlReports
}
