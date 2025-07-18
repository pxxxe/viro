//
//  ViroImageComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>

NS_ASSUME_NONNULL_BEGIN

@interface ViroImageComponentView : RCTViewComponentView

// Image source and content
- (void)setSource:(nullable NSDictionary *)source;
- (void)setPlaceholderSource:(nullable NSDictionary *)placeholderSource;

// Image dimensions
- (void)setWidth:(CGFloat)width;
- (void)setHeight:(CGFloat)height;

// Image display properties
- (void)setFormat:(nullable NSString *)format;
- (void)setMipmap:(BOOL)mipmap;
- (void)setWrapS:(nullable NSString *)wrapS;
- (void)setWrapT:(nullable NSString *)wrapT;
- (void)setMinificationFilter:(nullable NSString *)minificationFilter;
- (void)setMagnificationFilter:(nullable NSString *)magnificationFilter;
- (void)setResizeMode:(nullable NSString *)resizeMode;
- (void)setImageClipMode:(nullable NSString *)imageClipMode;

// Stereo image properties (for VR)
- (void)setStereoMode:(nullable NSString *)stereoMode;

// Material properties
- (void)setMaterials:(nullable NSArray<NSString *> *)materials;

// Event callbacks
- (void)setOnLoadStart:(nullable RCTBubblingEventBlock)onLoadStart;
- (void)setOnLoadEnd:(nullable RCTBubblingEventBlock)onLoadEnd;
- (void)setOnError:(nullable RCTBubblingEventBlock)onError;

@end

NS_ASSUME_NONNULL_END