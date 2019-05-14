/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game.Graphics;

/**
 * This class contains the default shaders used by the DefaultShader class. 
 * They are written in GLSL, or the OpenGL Shader Language. They are statically
 * returned as strings, so they may be passed into LWJGL to compile into shader
 * programs.
 *
 * @author alleew
 */
public class DefaultGLSLStrings 
{
    /*
    This is the GLSL code for the default vertex shader. The code is used from
    libGDX's default.vertex.glsl file.
    */
    private static String vertexShader = 
        "#if defined(diffuseTextureFlag) || defined(specularTextureFlag)\n" +
        "#define textureFlag\n" +
        "#endif\n" +
        "\n" +
        "#if defined(specularTextureFlag) || defined(specularColorFlag)\n" +
        "#define specularFlag\n" +
        "#endif\n" +
        "\n" +
        "#if defined(specularFlag) || defined(fogFlag)\n" +
        "#define cameraPositionFlag\n" +
        "#endif\n" +
        "\n" +
        "attribute vec3 a_position;\n" +
        "uniform mat4 u_projViewTrans;\n" +
        "\n" +
        "#if defined(colorFlag)\n" +
        "varying vec4 v_color;\n" +
        "attribute vec4 a_color;\n" +
        "#endif // colorFlag\n" +
        "\n" +
        "#ifdef normalFlag\n" +
        "attribute vec3 a_normal;\n" +
        "uniform mat3 u_normalMatrix;\n" +
        "varying vec3 v_normal;\n" +
        "#endif // normalFlag\n" +
        "\n" +
        "#ifdef textureFlag\n" +
        "attribute vec2 a_texCoord0;\n" +
        "#endif // textureFlag\n" +
        "\n" +
        "#ifdef diffuseTextureFlag\n" +
        "uniform vec4 u_diffuseUVTransform;\n" +
        "varying vec2 v_diffuseUV;\n" +
        "#endif\n" +
        "\n" +
        "#ifdef specularTextureFlag\n" +
        "uniform vec4 u_specularUVTransform;\n" +
        "varying vec2 v_specularUV;\n" +
        "#endif\n" +
        "\n" +
        "#ifdef boneWeight0Flag\n" +
        "#define boneWeightsFlag\n" +
        "attribute vec2 a_boneWeight0;\n" +
        "#endif //boneWeight0Flag\n" +
        "\n" +
        "#ifdef boneWeight1Flag\n" +
        "#ifndef boneWeightsFlag\n" +
        "#define boneWeightsFlag\n" +
        "#endif\n" +
        "attribute vec2 a_boneWeight1;\n" +
        "#endif //boneWeight1Flag\n" +
        "\n" +
        "#ifdef boneWeight2Flag\n" +
        "#ifndef boneWeightsFlag\n" +
        "#define boneWeightsFlag\n" +
        "#endif\n" +
        "attribute vec2 a_boneWeight2;\n" +
        "#endif //boneWeight2Flag\n" +
        "\n" +
        "#ifdef boneWeight3Flag\n" +
        "#ifndef boneWeightsFlag\n" +
        "#define boneWeightsFlag\n" +
        "#endif\n" +
        "attribute vec2 a_boneWeight3;\n" +
        "#endif //boneWeight3Flag\n" +
        "\n" +
        "#ifdef boneWeight4Flag\n" +
        "#ifndef boneWeightsFlag\n" +
        "#define boneWeightsFlag\n" +
        "#endif\n" +
        "attribute vec2 a_boneWeight4;\n" +
        "#endif //boneWeight4Flag\n" +
        "\n" +
        "#ifdef boneWeight5Flag\n" +
        "#ifndef boneWeightsFlag\n" +
        "#define boneWeightsFlag\n" +
        "#endif\n" +
        "attribute vec2 a_boneWeight5;\n" +
        "#endif //boneWeight5Flag\n" +
        "\n" +
        "#ifdef boneWeight6Flag\n" +
        "#ifndef boneWeightsFlag\n" +
        "#define boneWeightsFlag\n" +
        "#endif\n" +
        "attribute vec2 a_boneWeight6;\n" +
        "#endif //boneWeight6Flag\n" +
        "\n" +
        "#ifdef boneWeight7Flag\n" +
        "#ifndef boneWeightsFlag\n" +
        "#define boneWeightsFlag\n" +
        "#endif\n" +
        "attribute vec2 a_boneWeight7;\n" +
        "#endif //boneWeight7Flag\n" +
        "\n" +
        "#if defined(numBones) && defined(boneWeightsFlag)\n" +
        "#if (numBones > 0) \n" +
        "#define skinningFlag\n" +
        "#endif\n" +
        "#endif\n" +
        "\n" +
        "uniform mat4 u_worldTrans;\n" +
        "\n" +
        "#if defined(numBones)\n" +
        "#if numBones > 0\n" +
        "uniform mat4 u_bones[numBones];\n" +
        "#endif //numBones\n" +
        "#endif\n" +
        "\n" +
        "#ifdef shininessFlag\n" +
        "uniform float u_shininess;\n" +
        "#else\n" +
        "const float u_shininess = 20.0;\n" +
        "#endif // shininessFlag\n" +
        "\n" +
        "#ifdef blendedFlag\n" +
        "uniform float u_opacity;\n" +
        "varying float v_opacity;\n" +
        "\n" +
        "#ifdef alphaTestFlag\n" +
        "uniform float u_alphaTest;\n" +
        "varying float v_alphaTest;\n" +
        "#endif //alphaTestFlag\n" +
        "#endif // blendedFlag\n" +
        "\n" +
        "#ifdef lightingFlag\n" +
        "varying vec3 v_lightDiffuse;\n" +
        "\n" +
        "#ifdef ambientLightFlag\n" +
        "uniform vec3 u_ambientLight;\n" +
        "#endif // ambientLightFlag\n" +
        "\n" +
        "#ifdef ambientCubemapFlag\n" +
        "uniform vec3 u_ambientCubemap[6];\n" +
        "#endif // ambientCubemapFlag \n" +
        "\n" +
        "#ifdef sphericalHarmonicsFlag\n" +
        "uniform vec3 u_sphericalHarmonics[9];\n" +
        "#endif //sphericalHarmonicsFlag\n" +
        "\n" +
        "#ifdef specularFlag\n" +
        "varying vec3 v_lightSpecular;\n" +
        "#endif // specularFlag\n" +
        "\n" +
        "#ifdef cameraPositionFlag\n" +
        "uniform vec4 u_cameraPosition;\n" +
        "#endif // cameraPositionFlag\n" +
        "\n" +
        "#ifdef fogFlag\n" +
        "varying float v_fog;\n" +
        "#endif // fogFlag\n" +
        "\n" +
        "\n" +
        "#if defined(numDirectionalLights) && (numDirectionalLights > 0)\n" +
        "struct DirectionalLight\n" +
        "{\n" +
        "	vec3 color;\n" +
        "	vec3 direction;\n" +
        "};\n" +
        "uniform DirectionalLight u_dirLights[numDirectionalLights];\n" +
        "#endif // numDirectionalLights\n" +
        "\n" +
        "#if defined(numPointLights) && (numPointLights > 0)\n" +
        "struct PointLight\n" +
        "{\n" +
        "	vec3 color;\n" +
        "	vec3 position;\n" +
        "};\n" +
        "uniform PointLight u_pointLights[numPointLights];\n" +
        "#endif // numPointLights\n" +
        "\n" +
        "#if	defined(ambientLightFlag) || defined(ambientCubemapFlag) || defined(sphericalHarmonicsFlag)\n" +
        "#define ambientFlag\n" +
        "#endif //ambientFlag\n" +
        "\n" +
        "#ifdef shadowMapFlag\n" +
        "uniform mat4 u_shadowMapProjViewTrans;\n" +
        "varying vec3 v_shadowMapUv;\n" +
        "#define separateAmbientFlag\n" +
        "#endif //shadowMapFlag\n" +
        "\n" +
        "#if defined(ambientFlag) && defined(separateAmbientFlag)\n" +
        "varying vec3 v_ambientLight;\n" +
        "#endif //separateAmbientFlag\n" +
        "\n" +
        "#endif // lightingFlag\n" +
        "\n" +
        "void main() {\n" +
        "	#ifdef diffuseTextureFlag\n" +
        "		v_diffuseUV = u_diffuseUVTransform.xy + a_texCoord0 * u_diffuseUVTransform.zw;\n" +
        "	#endif //diffuseTextureFlag\n" +
        "	\n" +
        "	#ifdef specularTextureFlag\n" +
        "		v_specularUV = u_specularUVTransform.xy + a_texCoord0 * u_specularUVTransform.zw;\n" +
        "	#endif //specularTextureFlag\n" +
        "	\n" +
        "	#if defined(colorFlag)\n" +
        "		v_color = a_color;\n" +
        "	#endif // colorFlag\n" +
        "		\n" +
        "	#ifdef blendedFlag\n" +
        "		v_opacity = u_opacity;\n" +
        "		#ifdef alphaTestFlag\n" +
        "			v_alphaTest = u_alphaTest;\n" +
        "		#endif //alphaTestFlag\n" +
        "	#endif // blendedFlag\n" +
        "	\n" +
        "	#ifdef skinningFlag\n" +
        "		mat4 skinning = mat4(0.0);\n" +
        "		#ifdef boneWeight0Flag\n" +
        "			skinning += (a_boneWeight0.y) * u_bones[int(a_boneWeight0.x)];\n" +
        "		#endif //boneWeight0Flag\n" +
        "		#ifdef boneWeight1Flag				\n" +
        "			skinning += (a_boneWeight1.y) * u_bones[int(a_boneWeight1.x)];\n" +
        "		#endif //boneWeight1Flag\n" +
        "		#ifdef boneWeight2Flag		\n" +
        "			skinning += (a_boneWeight2.y) * u_bones[int(a_boneWeight2.x)];\n" +
        "		#endif //boneWeight2Flag\n" +
        "		#ifdef boneWeight3Flag\n" +
        "			skinning += (a_boneWeight3.y) * u_bones[int(a_boneWeight3.x)];\n" +
        "		#endif //boneWeight3Flag\n" +
        "		#ifdef boneWeight4Flag\n" +
        "			skinning += (a_boneWeight4.y) * u_bones[int(a_boneWeight4.x)];\n" +
        "		#endif //boneWeight4Flag\n" +
        "		#ifdef boneWeight5Flag\n" +
        "			skinning += (a_boneWeight5.y) * u_bones[int(a_boneWeight5.x)];\n" +
        "		#endif //boneWeight5Flag\n" +
        "		#ifdef boneWeight6Flag\n" +
        "			skinning += (a_boneWeight6.y) * u_bones[int(a_boneWeight6.x)];\n" +
        "		#endif //boneWeight6Flag\n" +
        "		#ifdef boneWeight7Flag\n" +
        "			skinning += (a_boneWeight7.y) * u_bones[int(a_boneWeight7.x)];\n" +
        "		#endif //boneWeight7Flag\n" +
        "	#endif //skinningFlag\n" +
        "\n" +
        "	#ifdef skinningFlag\n" +
        "		vec4 pos = u_worldTrans * skinning * vec4(a_position, 1.0);\n" +
        "	#else\n" +
        "		vec4 pos = u_worldTrans * vec4(a_position, 1.0);\n" +
        "	#endif\n" +
        "		\n" +
        "	gl_Position = u_projViewTrans * pos;\n" +
        "		\n" +
        "	#ifdef shadowMapFlag\n" +
        "		vec4 spos = u_shadowMapProjViewTrans * pos;\n" +
        "		v_shadowMapUv.xy = (spos.xy / spos.w) * 0.5 + 0.5;\n" +
        "		v_shadowMapUv.z = min(spos.z * 0.5 + 0.5, 0.998);\n" +
        "	#endif //shadowMapFlag\n" +
        "	\n" +
        "	#if defined(normalFlag)\n" +
        "		#if defined(skinningFlag)\n" +
        "			vec3 normal = normalize((u_worldTrans * skinning * vec4(a_normal, 0.0)).xyz);\n" +
        "		#else\n" +
        "			vec3 normal = normalize(u_normalMatrix * a_normal);\n" +
        "		#endif\n" +
        "		v_normal = normal;\n" +
        "	#endif // normalFlag\n" +
        "\n" +
        "    #ifdef fogFlag\n" +
        "        vec3 flen = u_cameraPosition.xyz - pos.xyz;\n" +
        "        float fog = dot(flen, flen) * u_cameraPosition.w;\n" +
        "        v_fog = min(fog, 1.0);\n" +
        "    #endif\n" +
        "\n" +
        "	#ifdef lightingFlag\n" +
        "		#if	defined(ambientLightFlag)\n" +
        "        	vec3 ambientLight = u_ambientLight;\n" +
        "		#elif defined(ambientFlag)\n" +
        "        	vec3 ambientLight = vec3(0.0);\n" +
        "		#endif\n" +
        "			\n" +
        "		#ifdef ambientCubemapFlag 		\n" +
        "			vec3 squaredNormal = normal * normal;\n" +
        "			vec3 isPositive  = step(0.0, normal);\n" +
        "			ambientLight += squaredNormal.x * mix(u_ambientCubemap[0], u_ambientCubemap[1], isPositive.x) +\n" +
        "					squaredNormal.y * mix(u_ambientCubemap[2], u_ambientCubemap[3], isPositive.y) +\n" +
        "					squaredNormal.z * mix(u_ambientCubemap[4], u_ambientCubemap[5], isPositive.z);\n" +
        "		#endif // ambientCubemapFlag\n" +
        "\n" +
        "		#ifdef sphericalHarmonicsFlag\n" +
        "			ambientLight += u_sphericalHarmonics[0];\n" +
        "			ambientLight += u_sphericalHarmonics[1] * normal.x;\n" +
        "			ambientLight += u_sphericalHarmonics[2] * normal.y;\n" +
        "			ambientLight += u_sphericalHarmonics[3] * normal.z;\n" +
        "			ambientLight += u_sphericalHarmonics[4] * (normal.x * normal.z);\n" +
        "			ambientLight += u_sphericalHarmonics[5] * (normal.z * normal.y);\n" +
        "			ambientLight += u_sphericalHarmonics[6] * (normal.y * normal.x);\n" +
        "			ambientLight += u_sphericalHarmonics[7] * (3.0 * normal.z * normal.z - 1.0);\n" +
        "			ambientLight += u_sphericalHarmonics[8] * (normal.x * normal.x - normal.y * normal.y);			\n" +
        "		#endif // sphericalHarmonicsFlag\n" +
        "\n" +
        "		#ifdef ambientFlag\n" +
        "			#ifdef separateAmbientFlag\n" +
        "				v_ambientLight = ambientLight;\n" +
        "				v_lightDiffuse = vec3(0.0);\n" +
        "			#else\n" +
        "				v_lightDiffuse = ambientLight;\n" +
        "			#endif //separateAmbientFlag\n" +
        "		#else\n" +
        "	        v_lightDiffuse = vec3(0.0);\n" +
        "		#endif //ambientFlag\n" +
        "\n" +
        "			\n" +
        "		#ifdef specularFlag\n" +
        "			v_lightSpecular = vec3(0.0);\n" +
        "			vec3 viewVec = normalize(u_cameraPosition.xyz - pos.xyz);\n" +
        "		#endif // specularFlag\n" +
        "			\n" +
        "		#if defined(numDirectionalLights) && (numDirectionalLights > 0) && defined(normalFlag)\n" +
        "			for (int i = 0; i < numDirectionalLights; i++) {\n" +
        "				vec3 lightDir = -u_dirLights[i].direction;\n" +
        "				float NdotL = clamp(dot(normal, lightDir), 0.0, 1.0);\n" +
        "				vec3 value = u_dirLights[i].color * NdotL;\n" +
        "				v_lightDiffuse += value;\n" +
        "				#ifdef specularFlag\n" +
        "					float halfDotView = max(0.0, dot(normal, normalize(lightDir + viewVec)));\n" +
        "					v_lightSpecular += value * pow(halfDotView, u_shininess);\n" +
        "				#endif // specularFlag\n" +
        "			}\n" +
        "		#endif // numDirectionalLights\n" +
        "\n" +
        "		#if defined(numPointLights) && (numPointLights > 0) && defined(normalFlag)\n" +
        "			for (int i = 0; i < numPointLights; i++) {\n" +
        "				vec3 lightDir = u_pointLights[i].position - pos.xyz;\n" +
        "				float dist2 = dot(lightDir, lightDir);\n" +
        "				lightDir *= inversesqrt(dist2);\n" +
        "				float NdotL = clamp(dot(normal, lightDir), 0.0, 1.0);\n" +
        "				vec3 value = u_pointLights[i].color * (NdotL / (1.0 + dist2));\n" +
        "				v_lightDiffuse += value;\n" +
        "				#ifdef specularFlag\n" +
        "					float halfDotView = max(0.0, dot(normal, normalize(lightDir + viewVec)));\n" +
        "					v_lightSpecular += value * pow(halfDotView, u_shininess);\n" +
        "				#endif // specularFlag\n" +
        "			}\n" +
        "		#endif // numPointLights\n" +
        "	#endif // lightingFlag\n" +
        "}";
    
    /*
    This is the GLSL code for the default vertex shader. The code is used from
    libGDX's default.fragment.glsl file.
    */
    private static String fragmentShader = 
        "#ifdef GL_ES \n" +
        "#define LOWP lowp\n" +
        "#define MED mediump\n" +
        "#define HIGH highp\n" +
        "precision mediump float;\n" +
        "#else\n" +
        "#define MED\n" +
        "#define LOWP\n" +
        "#define HIGH\n" +
        "#endif\n" +
        "\n" +
        "#if defined(specularTextureFlag) || defined(specularColorFlag)\n" +
        "#define specularFlag\n" +
        "#endif\n" +
        "\n" +
        "#ifdef normalFlag\n" +
        "varying vec3 v_normal;\n" +
        "#endif //normalFlag\n" +
        "\n" +
        "#if defined(colorFlag)\n" +
        "varying vec4 v_color;\n" +
        "#endif\n" +
        "\n" +
        "#ifdef blendedFlag\n" +
        "varying float v_opacity;\n" +
        "#ifdef alphaTestFlag\n" +
        "varying float v_alphaTest;\n" +
        "#endif //alphaTestFlag\n" +
        "#endif //blendedFlag\n" +
        "\n" +
        "#if defined(diffuseTextureFlag) || defined(specularTextureFlag)\n" +
        "#define textureFlag\n" +
        "#endif\n" +
        "\n" +
        "#ifdef diffuseTextureFlag\n" +
        "varying MED vec2 v_diffuseUV;\n" +
        "#endif\n" +
        "\n" +
        "#ifdef specularTextureFlag\n" +
        "varying MED vec2 v_specularUV;\n" +
        "#endif\n" +
        "\n" +
        "#ifdef diffuseColorFlag\n" +
        "uniform vec4 u_diffuseColor;\n" +
        "#endif\n" +
        "\n" +
        "#ifdef diffuseTextureFlag\n" +
        "uniform sampler2D u_diffuseTexture;\n" +
        "#endif\n" +
        "\n" +
        "#ifdef specularColorFlag\n" +
        "uniform vec4 u_specularColor;\n" +
        "#endif\n" +
        "\n" +
        "#ifdef specularTextureFlag\n" +
        "uniform sampler2D u_specularTexture;\n" +
        "#endif\n" +
        "\n" +
        "#ifdef normalTextureFlag\n" +
        "uniform sampler2D u_normalTexture;\n" +
        "#endif\n" +
        "\n" +
        "#ifdef lightingFlag\n" +
        "varying vec3 v_lightDiffuse;\n" +
        "\n" +
        "#if	defined(ambientLightFlag) || defined(ambientCubemapFlag) || defined(sphericalHarmonicsFlag)\n" +
        "#define ambientFlag\n" +
        "#endif //ambientFlag\n" +
        "\n" +
        "#ifdef specularFlag\n" +
        "varying vec3 v_lightSpecular;\n" +
        "#endif //specularFlag\n" +
        "\n" +
        "#ifdef shadowMapFlag\n" +
        "uniform sampler2D u_shadowTexture;\n" +
        "uniform float u_shadowPCFOffset;\n" +
        "varying vec3 v_shadowMapUv;\n" +
        "#define separateAmbientFlag\n" +
        "\n" +
        "float getShadowness(vec2 offset)\n" +
        "{\n" +
        "    const vec4 bitShifts = vec4(1.0, 1.0 / 255.0, 1.0 / 65025.0, 1.0 / 160581375.0);\n" +
        "    return step(v_shadowMapUv.z, dot(texture2D(u_shadowTexture, v_shadowMapUv.xy + offset), bitShifts));//+(1.0/255.0));	\n" +
        "}\n" +
        "\n" +
        "float getShadow() \n" +
        "{\n" +
        "	return (//getShadowness(vec2(0,0)) + \n" +
        "			getShadowness(vec2(u_shadowPCFOffset, u_shadowPCFOffset)) +\n" +
        "			getShadowness(vec2(-u_shadowPCFOffset, u_shadowPCFOffset)) +\n" +
        "			getShadowness(vec2(u_shadowPCFOffset, -u_shadowPCFOffset)) +\n" +
        "			getShadowness(vec2(-u_shadowPCFOffset, -u_shadowPCFOffset))) * 0.25;\n" +
        "}\n" +
        "#endif //shadowMapFlag\n" +
        "\n" +
        "#if defined(ambientFlag) && defined(separateAmbientFlag)\n" +
        "varying vec3 v_ambientLight;\n" +
        "#endif //separateAmbientFlag\n" +
        "\n" +
        "#endif //lightingFlag\n" +
        "\n" +
        "#ifdef fogFlag\n" +
        "uniform vec4 u_fogColor;\n" +
        "varying float v_fog;\n" +
        "#endif // fogFlag\n" +
        "\n" +
        "void main() {\n" +
        "	#if defined(normalFlag) \n" +
        "		vec3 normal = v_normal;\n" +
        "	#endif // normalFlag\n" +
        "		\n" +
        "	#if defined(diffuseTextureFlag) && defined(diffuseColorFlag) && defined(colorFlag)\n" +
        "		vec4 diffuse = texture2D(u_diffuseTexture, v_diffuseUV) * u_diffuseColor * v_color;\n" +
        "	#elif defined(diffuseTextureFlag) && defined(diffuseColorFlag)\n" +
        "		vec4 diffuse = texture2D(u_diffuseTexture, v_diffuseUV) * u_diffuseColor;\n" +
        "	#elif defined(diffuseTextureFlag) && defined(colorFlag)\n" +
        "		vec4 diffuse = texture2D(u_diffuseTexture, v_diffuseUV) * v_color;\n" +
        "	#elif defined(diffuseTextureFlag)\n" +
        "		vec4 diffuse = texture2D(u_diffuseTexture, v_diffuseUV);\n" +
        "	#elif defined(diffuseColorFlag) && defined(colorFlag)\n" +
        "		vec4 diffuse = u_diffuseColor * v_color;\n" +
        "	#elif defined(diffuseColorFlag)\n" +
        "		vec4 diffuse = u_diffuseColor;\n" +
        "	#elif defined(colorFlag)\n" +
        "		vec4 diffuse = v_color;\n" +
        "	#else\n" +
        "		vec4 diffuse = vec4(1.0);\n" +
        "	#endif\n" +
        "\n" +
        "	#if (!defined(lightingFlag))  \n" +
        "		gl_FragColor.rgb = diffuse.rgb;\n" +
        "	#elif (!defined(specularFlag))\n" +
        "		#if defined(ambientFlag) && defined(separateAmbientFlag)\n" +
        "			#ifdef shadowMapFlag\n" +
        "				gl_FragColor.rgb = (diffuse.rgb * (v_ambientLight + getShadow() * v_lightDiffuse));\n" +
        "				//gl_FragColor.rgb = texture2D(u_shadowTexture, v_shadowMapUv.xy);\n" +
        "			#else\n" +
        "				gl_FragColor.rgb = (diffuse.rgb * (v_ambientLight + v_lightDiffuse));\n" +
        "			#endif //shadowMapFlag\n" +
        "		#else\n" +
        "			#ifdef shadowMapFlag\n" +
        "				gl_FragColor.rgb = getShadow() * (diffuse.rgb * v_lightDiffuse);\n" +
        "			#else\n" +
        "				gl_FragColor.rgb = (diffuse.rgb * v_lightDiffuse);\n" +
        "			#endif //shadowMapFlag\n" +
        "		#endif\n" +
        "	#else\n" +
        "		#if defined(specularTextureFlag) && defined(specularColorFlag)\n" +
        "			vec3 specular = texture2D(u_specularTexture, v_specularUV).rgb * u_specularColor.rgb * v_lightSpecular;\n" +
        "		#elif defined(specularTextureFlag)\n" +
        "			vec3 specular = texture2D(u_specularTexture, v_specularUV).rgb * v_lightSpecular;\n" +
        "		#elif defined(specularColorFlag)\n" +
        "			vec3 specular = u_specularColor.rgb * v_lightSpecular;\n" +
        "		#else\n" +
        "			vec3 specular = v_lightSpecular;\n" +
        "		#endif\n" +
        "			\n" +
        "		#if defined(ambientFlag) && defined(separateAmbientFlag)\n" +
        "			#ifdef shadowMapFlag\n" +
        "			gl_FragColor.rgb = (diffuse.rgb * (getShadow() * v_lightDiffuse + v_ambientLight)) + specular;\n" +
        "				//gl_FragColor.rgb = texture2D(u_shadowTexture, v_shadowMapUv.xy);\n" +
        "			#else\n" +
        "				gl_FragColor.rgb = (diffuse.rgb * (v_lightDiffuse + v_ambientLight)) + specular;\n" +
        "			#endif //shadowMapFlag\n" +
        "		#else\n" +
        "			#ifdef shadowMapFlag\n" +
        "				gl_FragColor.rgb = getShadow() * ((diffuse.rgb * v_lightDiffuse) + specular);\n" +
        "			#else\n" +
        "				gl_FragColor.rgb = (diffuse.rgb * v_lightDiffuse) + specular;\n" +
        "			#endif //shadowMapFlag\n" +
        "		#endif\n" +
        "	#endif //lightingFlag\n" +
        "\n" +
        "	#ifdef fogFlag\n" +
        "		gl_FragColor.rgb = mix(gl_FragColor.rgb, u_fogColor.rgb, v_fog);\n" +
        "	#endif // end fogFlag\n" +
        "\n" +
        "	#ifdef blendedFlag\n" +
        "		gl_FragColor.a = diffuse.a * v_opacity;\n" +
        "		#ifdef alphaTestFlag\n" +
        "			if (gl_FragColor.a <= v_alphaTest)\n" +
        "				discard;\n" +
        "		#endif\n" +
        "	#else\n" +
        "		gl_FragColor.a = 1.0;\n" +
        "	#endif\n" +
        "\n" +
        "}";
    
    public static String GetDefaultVertexShader()
    {
        return vertexShader;
    }
    
    public static String GetDefaultFragmentShader()
    {
        return fragmentShader;
    }
}
