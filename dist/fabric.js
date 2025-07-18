"use strict";
/**
 * Viro React Native - New Architecture Direct Import
 *
 * This entry point provides access to Viro components that are compatible with
 * React Native's New Architecture (Fabric). All components now use direct
 * Fabric integration without an interop layer.
 *
 * @example
 * ```
 * import { ViroARScene, Viro3DObject } from '@reactvision/react-viro/fabric';
 * ```
 */
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    var desc = Object.getOwnPropertyDescriptor(m, k);
    if (!desc || ("get" in desc ? !m.__esModule : desc.writable || desc.configurable)) {
      desc = { enumerable: true, get: function() { return m[k]; } };
    }
    Object.defineProperty(o, k2, desc);
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __exportStar = (this && this.__exportStar) || function(m, exports) {
    for (var p in m) if (p !== "default" && !Object.prototype.hasOwnProperty.call(exports, p)) __createBinding(exports, m, p);
};
Object.defineProperty(exports, "__esModule", { value: true });
// Direct export from main index with New Architecture validation
__exportStar(require("./index"), exports);
